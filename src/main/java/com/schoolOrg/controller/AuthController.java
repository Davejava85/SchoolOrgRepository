package com.schoolOrg.controller;

import com.schoolOrg.configuration.security.JwtService;
import com.schoolOrg.domain.dto.LoginRequestDTO;
import com.schoolOrg.domain.dto.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            // 1. Autentica le credenziali
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // 2. Genera il JWT Token
            String jwtToken = jwtService.generateToken(authentication);

            // 3. Restituisci il token
            return ResponseEntity.ok(new LoginResponseDTO(jwtToken));

        } catch (Exception ex) {
            // Restituisce un errore in caso di credenziali errate
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }









}
