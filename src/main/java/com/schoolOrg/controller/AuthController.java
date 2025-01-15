package com.schoolOrg.controller;

import com.schoolOrg.configuration.security.JwtService;
import com.schoolOrg.domain.dto.LoginRequestDTO;
import com.schoolOrg.domain.dto.LoginResponseDTO;
import com.schoolOrg.domain.dto.RegistrationDTO;
import com.schoolOrg.domain.dto.StudentiDTO;
import com.schoolOrg.domain.repository.ProfessoriRepository;
import com.schoolOrg.service.ProfessoriService;
import com.schoolOrg.service.RegistrationService;
import com.schoolOrg.service.StudentiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private StudentiService studentiService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProfessoriRepository professoriRepository;

    @Autowired
    private ProfessoriService professoriService;

    @Autowired
    private RegistrationService registartionService;


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

    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody RegistrationDTO registrationRequest) {
//        // Verifica che l'email non sia già usata
//        if (studentiService.findStudentByEmail(registrationRequest.getEmail()) != null &&
//                professoriRepository.findByEmail(registrationRequest.getEmail()) != null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Email already in use");
//        }
//
//        if (registrationRequest.getRuolo().equals("studente")) {
//
//
//
//        // Crittografia della password
////        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
////
////        // Creazione dell'utente con ruolo predefinito
////        StudentiDTO newUser = new StudentiDTO();
////        newUser.setEmail(registrationRequest.getEmail());
////        newUser.setPassword(encodedPassword);
////        newUser.setRole("ROLE_USER");
////        userService.save(newUser);
//
//        StudentiDTO createdStudent = studentiService.createStudente(registrationRequest);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
//
//    }else if (registrationRequest.getRuolo().equals("professore")) {
//
//            StudentiDTO createdProfessor = professoriService.createProfessori(registrationRequest)
//
//        }
//
//
//
//        }

    public ResponseEntity<?> register(@RequestBody RegistrationDTO request) {
//        try {
        registartionService.register(request);
        return ResponseEntity.ok("User registered successfully");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }
    }
}







