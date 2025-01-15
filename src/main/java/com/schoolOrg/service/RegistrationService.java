package com.schoolOrg.service;

import com.schoolOrg.domain.dto.RegistrationDTO;
import com.schoolOrg.domain.model.Professori;
import com.schoolOrg.domain.model.Studenti;
import com.schoolOrg.domain.repository.ProfessoriRepository;
import com.schoolOrg.domain.repository.StudentiRepository;
import com.schoolOrg.exception.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final StudentiRepository studentiRepository;
    private final ProfessoriRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(StudentiRepository studentiRepository,
                               ProfessoriRepository professoriRepository,
                               PasswordEncoder passwordEncoder) {
        this.studentiRepository = studentiRepository;
        this.professorRepository = professoriRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegistrationDTO request) {
        // Controlla se l'email è già registrata
        if (isEmailRegistered(request.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }

        // Registra in base al ruolo
        if ("STUDENTE".equalsIgnoreCase(request.getRuolo())) {
            registerStudent(request);
        } else if ("PROFESSORE".equalsIgnoreCase(request.getRuolo())) {
            registerProfessor(request);
        } else {
            throw new BadRequestException("Invalid role: " + request.getRuolo());
        }
    }

    private boolean isEmailRegistered(String email) {
        return studentiRepository.findByEmail(email).isPresent() || professorRepository.findByEmail(email) != null;
    }

    private void registerStudent(RegistrationDTO request) {
        Studenti studenti = new Studenti();
        studenti.setNome(request.getNome());
        studenti.setCognome(request.getCognome());
        studenti.setPassword(passwordEncoder.encode(request.getPassword()));
        studenti.setEmail(request.getEmail());
        studenti.setRuolo(request.getRuolo());
        studentiRepository.save(studenti);
    }

    private void registerProfessor(RegistrationDTO request) {
        Professori professor = new Professori();
        professor.setNome(request.getNome());
        professor.setCognome(request.getCognome());
        professor.setPassword(passwordEncoder.encode(request.getPassword()));
        professor.setEmail(request.getEmail());
        professor.setRuolo(request.getRuolo());
        professorRepository.save(professor);
    }
}











