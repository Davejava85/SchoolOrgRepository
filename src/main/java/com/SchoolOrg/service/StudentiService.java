package com.SchoolOrg.service;

import com.SchoolOrg.domain.dto.StudentiDTO;
import com.SchoolOrg.domain.model.Studenti;
import com.SchoolOrg.domain.repository.StudentiRepository;
import com.SchoolOrg.service.mapper.StudentiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            throw new RuntimeException("No student found");
        } else {
            //     METODO CLASSICO
            List<StudentiDTO> allStudentDto = studentiMapper.toDTO(allStudents);
          //  return studentiMapper.toDTO(allStudents);
            return allStudentDto;
//            return allStudents
//                    .stream()
//                    .map(StudentiMapper.INSTANCE::toDTO)
//                    .collect(Collectors.toList());
//        }

        }
    }

    public List<StudentiDTO> findByLetter(String letter) {
        List<Studenti> allStudents = studentiRepository.findAll();

        List<StudentiDTO> studentWithLetter= allStudents.stream()
                .filter(studente -> studente.getNome().startsWith(letter))  // Usa lettera come filtro
                .map(StudentiMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        if (studentWithLetter.isEmpty()) {
            throw new RuntimeException("There are not name with" + letter);
        }
        return studentWithLetter;
    }


    public StudentiDTO findStudentByEmail(String email) {
        Studenti studentEmail = studentiRepository.findByEmail(email);
        return StudentiMapper.INSTANCE.toDTO(studentEmail);

    }
}