package com.schoolOrg.service;
;
import com.schoolOrg.domain.dto.StudentiDTO;
import com.schoolOrg.domain.model.Studenti;
import com.schoolOrg.domain.repository.StudentiRepository;
import com.schoolOrg.exception.BadRequestException;
import com.schoolOrg.exception.ResourceNotFoundException;
import com.schoolOrg.service.mapper.StudentiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentiService {



    @Autowired
    private StudentiRepository studentiRepository;

    @Autowired
    private StudentiMapper studentiMapper;


    public List<StudentiDTO> findAllStudent() {
        List<Studenti> allStudents = studentiRepository.findAll();
        if (allStudents.isEmpty()) {
            throw new ResourceNotFoundException("No student found");
        } else {
            //     METODO CLASSICO
            List<StudentiDTO> allStudentDto = studentiMapper.toDTOList(allStudents);
            //  return studentiMapper.toDTO(allStudents);
       //     return allStudentDto;
            return allStudents
                    .stream()
                    .map(studentiMapper::toDTO)
                    .collect(Collectors.toList());
        }

        }


    public List<StudentiDTO> findByLetter(String letter) {
        List<Studenti> allStudents = studentiRepository.findAll();

        List<StudentiDTO> studentWithLetter = allStudents.stream()
                .filter(s -> s.getNome().startsWith(letter))  // Usa lettera come filtro
                .map(studentiMapper::toDTO)
                .collect(Collectors.toList());

        if (studentWithLetter.isEmpty()) {
            throw new ResourceNotFoundException("There are not name with" + letter);
        }
        return studentWithLetter;
    }


    public StudentiDTO findStudentByEmail(String email) {
        Optional<Studenti> studentEmailOpt = studentiRepository.findByEmail(email);
        return studentEmailOpt
                .map(studentiMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Student with email " + email + " not found"));
    }


    public StudentiDTO createStudente(StudentiDTO studentiDTO) {
        Optional<Studenti> existingStudenteOpt = studentiRepository.findByEmail(studentiDTO.getEmail());
        if (existingStudenteOpt.isEmpty()) {
            Studenti studenti = studentiMapper.toEntity(studentiDTO);
            studenti = studentiRepository.save(studenti);
            return studentiMapper.toDTO(studenti);
        } else {
            throw new BadRequestException("Student already exist");
        }
    }


    public StudentiDTO updateStudente(StudentiDTO studentiDTO) {
        Optional<Studenti> existingStudenteOpt = studentiRepository.findById(studentiDTO.getId());

        if (existingStudenteOpt.isPresent()) {
            Studenti studenti = studentiMapper.toEntity(studentiDTO);
            studenti = studentiRepository.save(studenti);
            return studentiMapper.toDTO(studenti);

        } else {
            throw new ResourceNotFoundException("STUDENT DOES NOT EXIST");
        }
    }
}




