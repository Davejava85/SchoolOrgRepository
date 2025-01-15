package com.schoolOrg.configuration.security;

import com.schoolOrg.domain.model.Professori;
import com.schoolOrg.domain.model.Studenti;
import com.schoolOrg.domain.repository.ProfessoriRepository;
import com.schoolOrg.domain.repository.StudentiRepository;
import com.schoolOrg.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public  class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentiRepository studentiRepository;

    @Autowired
    private ProfessoriRepository professoriRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Cerca l'utente nel database tramite email
        Optional<Studenti> studentiOpt = studentiRepository.findByEmail(email);

        Professori professori = professoriRepository.findByEmail(email);

        if (studentiOpt.isPresent()) {
            Studenti studente = studentiOpt.get();
            return new org.springframework.security.core.userdetails.User(
                    studente.getEmail(),
                    studente.getPassword(),
                    new ArrayList<>());
        } else if (professori != null) {
            return new org.springframework.security.core.userdetails.User(
                    professori.getEmail(),
                    professori.getPassword(),
                    new ArrayList<>());
        } else {
            throw new ResourceNotFoundException("email does not exist");
        }


//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));


    }

}
