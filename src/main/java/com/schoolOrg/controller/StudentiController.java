package com.schoolOrg.controller;


import com.schoolOrg.domain.dto.StudentiDTO;
import com.schoolOrg.domain.repository.StudentiRepository;
import com.schoolOrg.service.StudentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentiController {

    @Autowired
    private StudentiService studentiService;

    @Autowired
    private StudentiRepository studentiRepository;

    @GetMapping("/all")
   // public List <StudentiDTO> findAllStudent() {
    //    return studentiService.findAllStudent();
        // se si necessita di una response entity
        public ResponseEntity<List<StudentiDTO>> findAllStudent () {
            List<StudentiDTO> allStudentDto = studentiService.findAllStudent();
            // Restituisci una ResponseEntity con status 200 (OK) e la lista di studenti
            return new ResponseEntity<>(allStudentDto, HttpStatus.OK);
        }



    @GetMapping("/{email}")
    public ResponseEntity<StudentiDTO> findStudentByEmaill(@PathVariable String email) {
        StudentiDTO studentiDTO = studentiService.findStudentByEmail(email);
        return new ResponseEntity<>(studentiDTO, HttpStatus.OK);

    }

    @GetMapping("startWith/{letter}")
    public ResponseEntity <List<StudentiDTO>> findStudentByLetter(@PathVariable String letter) {
        List<StudentiDTO> studentiDTO = studentiService.findByLetter(letter);
        return new ResponseEntity<>(studentiDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity <StudentiDTO> createStudent (@RequestBody StudentiDTO studentiDTO) {
        StudentiDTO createdStudent = studentiService.createStudente(studentiDTO);
        return new ResponseEntity<> (createdStudent, HttpStatus.CREATED);

    }

    @PutMapping("/update")
    public ResponseEntity <StudentiDTO> updateStudent (@RequestBody StudentiDTO studentiDTO) {
        StudentiDTO createdStudent = studentiService.updateStudente(studentiDTO);
        return new ResponseEntity<> (createdStudent, HttpStatus.OK);


    }

}
