package com.schoolOrg.configuration.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private  JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

//        String path = request.getRequestURI();
//        if (path.startsWith("/api/auth/")) {
//            filterChain.doFilter(request, response); // Non eseguire il filtro JWT sulla rotta di login
//            return;
//        }

        // Estrai il token dall'header Authorization
        String authHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7); // Rimuove "Bearer " dall'header
            try {
                username = jwtService.extractUsername(jwtToken); // Estrai username dal token
            } catch (Exception e) {
                logger.error("Errore nel parsing del token JWT: ", e);
            }
        }

        // Se l'username è valido e non esiste un'authentication già nel SecurityContext, configura l'utente nel contesto di sicurezza
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username); // Carica i dettagli dell'utente

                if (jwtService.isTokenValid(jwtToken, userDetails)) {
                    // Se il token è valido, crea un oggetto di autenticazione e lo inserisce nel SecurityContext
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (Exception e) {
                logger.error("Errore durante la validazione del token JWT: ", e);
            }
        }

        // Continua con la catena dei filtri
        filterChain.doFilter(request, response);
    }
}




