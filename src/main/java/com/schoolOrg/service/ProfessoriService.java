package com.schoolOrg.service;

import com.schoolOrg.domain.dto.ProfessoriDTO;
import com.schoolOrg.domain.dto.StudentiDTO;
import com.schoolOrg.domain.model.Professori;
import com.schoolOrg.domain.model.Studenti;
import com.schoolOrg.domain.repository.ProfessoriRepository;
import com.schoolOrg.exception.BadRequestException;
import com.schoolOrg.service.mapper.ProfessoriMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class ProfessoriService {

    @Autowired
    private ProfessoriRepository professoriRepository;

    @Autowired
    private ProfessoriMapper professoriMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public ProfessoriDTO createProfessori(ProfessoriDTO professoriDTO) {
        if (professoriRepository.findByEmail(professoriDTO.getEmail()) != null) {
            throw new BadRequestException("Student already exists with email: " + professoriDTO.getEmail());
        }
        Professori professori= professoriMapper.toEntity(professoriDTO);
        professori.setPassword(passwordEncoder.encode(professoriDTO.getPassword()));
        professori = professoriRepository.save(professori);
        return professoriMapper.toDTO(professori);

    }


}
