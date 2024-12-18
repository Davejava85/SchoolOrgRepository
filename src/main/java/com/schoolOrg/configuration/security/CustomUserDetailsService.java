package com.schoolOrg.configuration.security;

import com.schoolOrg.domain.model.Studenti;
import com.schoolOrg.domain.repository.StudentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public  class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentiRepository studentiRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Cerca l'utente nel database tramite email
        Studenti studentiEntity = studentiRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                studentiEntity.getEmail(),
                studentiEntity.getPassword(),
                new ArrayList<>());


    }
}
