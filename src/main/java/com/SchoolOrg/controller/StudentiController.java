package com.SchoolOrg.controller;


import com.SchoolOrg.domain.dto.StudentiDTO;
import com.SchoolOrg.service.StudentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentiController {

    @Autowired
    private StudentiService studentiService;

    @GetMapping("/all")
//    public List <StudentiDTO> findAllStudent() {
//        return studentiService.findAllStudent();
    // se si necessita di una response entity
    public ResponseEntity<List<StudentiDTO>> findAllStudent() {
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
    public ResponseEntity <List<StudentiDTO>> findStudentByA(@PathVariable String letter) {
        List<StudentiDTO> studentiDTO = studentiService.findByLetter(letter);
        return new ResponseEntity<>(studentiDTO, HttpStatus.OK);
    }
}
