package com.schoolOrg;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schoolOrg.controller.StudentiController;
import com.schoolOrg.domain.dto.StudentiDTO;
import com.schoolOrg.domain.repository.StudentiRepository;
import com.schoolOrg.service.StudentiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;


import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc  //Questa annotazione abilita la configurazione automatica di MockMvc, un oggetto che permette di
// simulare richieste HTTP e verificare le risposte, senza dover avviare un server.
public class StudentiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentiService studentiService;

    @Mock
    private StudentiRepository studentiRepository;

    @InjectMocks
    private StudentiController studentiController;

    @Autowired
    private ObjectMapper objectMapper;

    private StudentiDTO studentiDTO;

    @BeforeEach
    public void setUp() {
        studentiDTO = new StudentiDTO();
        studentiDTO.setId(1);
        studentiDTO.setNome("John");
        studentiDTO.setEmail("john.doe@example.com");
    }

    @Test
    public void testFindAllStudent() throws Exception {
        when(studentiService.findAllStudent()).thenReturn(Arrays.asList(studentiDTO));

        mockMvc.perform(get("/api/student/all")) // simula la chiamata
                .andExpect(status().isOk()) // dice cosa ci si aspetta coem risposta
                .andExpect(jsonPath("$", hasSize(greaterThan(0)))) // verificando che la risposta contenga
                // almeno un elemento. jsonPath è una sintassi che permette di navigare attraverso il JSON restituito
                // dalla risposta. In questo caso, $ rappresenta l'intero corpo della risposta,
                // e hasSize(greaterThan(0)) verifica che la lista degli studenti restituita contenga almeno un elemento.
                .andExpect(jsonPath("$[0].nome").value("John")) // verifica del valore nome
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));



        verify(studentiService, times(1)).findAllStudent();
    }

    @Test
    public void testFindStudentByEmail() throws Exception {
        when(studentiService.findStudentByEmail("john.doe@example.com")).thenReturn(studentiDTO);

        mockMvc.perform(get("/api/student/{email}", "john.doe@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("John"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(studentiService, times(1)).findStudentByEmail("john.doe@example.com");
    }

    @Test
    public void testCreateStudent() throws Exception {
        when(studentiService.createStudente(any(StudentiDTO.class))).thenReturn(studentiDTO);

        mockMvc.perform(post("/api/student/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentiDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("John"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(studentiService, times(1)).createStudente(any(StudentiDTO.class));
    }

    @Test
    public void testUpdateStudent() throws Exception {
        when(studentiService.updateStudente(any(StudentiDTO.class))).thenReturn(studentiDTO);

        mockMvc.perform(put("/api/student/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentiDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("John"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(studentiService, times(1)).updateStudente(any(StudentiDTO.class));
    }
}