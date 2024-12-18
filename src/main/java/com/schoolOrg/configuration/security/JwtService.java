package com.schoolOrg.configuration.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class JwtService {


        @Value("${jwt.secret}")
        private String secretKey;  // La chiave segreta per firmare il JWT

        @Value("${jwt.expiration}")
        private long expirationTime;  // Tempo di scadenza del token (es. 3600000 ms = 1 ora)

        // Metodo per generare il token JWT
        public String generateToken(Authentication authentication) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            return Jwts.builder()
                    .setSubject(userDetails.getUsername())  // Setta il nome utente come soggetto
                    .setIssuedAt(new Date())  // Imposta la data di emissione
                    .setExpiration(new Date(System.currentTimeMillis() + expirationTime))  // Imposta la scadenza
                    .signWith(SignatureAlgorithm.HS256, secretKey)  // Firma il token con la chiave segreta
                    .compact();  // Crea il token
        }

        // Metodo per estrarre il nome utente dal token
        public String extractUsername(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();  // Restituisce il soggetto (username)
        }

        // Metodo per validare il token
        public boolean isTokenValid(String token, UserDetails userDetails) {
            String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }

        // Metodo per verificare se il token è scaduto
        private boolean isTokenExpired(String token) {
            Date expiration = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
            return expiration.before(new Date());
        }
    }


