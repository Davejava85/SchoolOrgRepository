package com.schoolOrg;

import com.schoolOrg.domain.dto.StudentiDTO;
import com.schoolOrg.domain.model.Studenti;
import com.schoolOrg.domain.repository.StudentiRepository;
import com.schoolOrg.service.StudentiService;
import com.schoolOrg.service.mapper.StudentiMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SchoolOrgApplicationTests {


    @Mock //Per creare dipendenze simulate che controlli direttamente nel test.
    private StudentiRepository studentiRepository;

    @Mock
    private StudentiMapper studentiMapper;


    @InjectMocks // Per testare la logica di una classe reale, senza dover configurare manualmente le sue dipendenze.
    private StudentiService studentiService;

    @Test
    void testFindStudentByEmail_Success() {
        // Arrange
        String email = "test@example.com";
        Studenti mockStudent = new Studenti(); // Supponendo che sia l'entità
        mockStudent.setEmail(email);

        StudentiDTO mockStudentDTO = new StudentiDTO(); // Supponendo che sia il DTO
        mockStudentDTO.setEmail(email);

        when(studentiRepository.findByEmail(email)).thenReturn(Optional.of(mockStudent));
        when(studentiMapper.toDTO(mockStudent)).thenReturn(mockStudentDTO);

        // Act
        StudentiDTO result = studentiService.findStudentByEmail(email);

        // Assert
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(studentiRepository, times(1)).findByEmail(email);
        verify(studentiMapper, times(1)).toDTO(mockStudent);
    }

    @Test
    void testFindStudentByEmail_NotFound() {
        // Arrange
        String email = "notfound@example.com";
        when(studentiRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentiService.findStudentByEmail(email);
        });

        assertEquals("Student with email " + email + " not found", exception.getMessage());
        verify(studentiRepository, times(1)).findByEmail(email);
        verifyNoInteractions(studentiMapper); // Il mapper non deve essere chiamato
    }

    @Test
    void testFindStudentByLetter_Success() {
        // Arrange
        String letter = "G";

        // Crea una lista di studenti simulati visto che non vi è accesso al database
        List<Studenti> mockStudents = List.of(
                new Studenti(1, "Giovanni", "Rossi", "giovanni.rossi@example.com"),
                new Studenti(2, "Giorgia", "Bianchi", "giorgia.bianchi@example.com"),
                new Studenti(3, "Mario", "Verdi", "mario.verdi@example.com")
        );

        // Crea la lista di DTO corrispondente che soddifini il requisito di iniziare con la G
        List<StudentiDTO> mockStudentDTOs = List.of(
                new StudentiDTO(1, "Giovanni", "Rossi", "giovanni.rossi@example.com"),
                new StudentiDTO(2, "Giorgia", "Bianchi", "giorgia.bianchi@example.com")
        );
        // testiamo il findAll presente nel metodo
        when(studentiRepository.findAll()).thenReturn(mockStudents);


        //Act
        // ci aspettiamo che ci ritorni i 2 elementi della lista che soddifano il requisito
        when(studentiMapper.toDTO(mockStudents.get(0))).thenReturn(mockStudentDTOs.get(0));
        when(studentiMapper.toDTO(mockStudents.get(1))).thenReturn(mockStudentDTOs.get(1));

        // ora che abbiamo creato tutti i presupposti (oggetti Studenti, StudentiDto e abbiami definiti quale comportamento ci aspettiamo
        // testiano il metodo della classe service
        List<StudentiDTO> result = studentiService.findByLetter(letter);

        // Assert quest o deve essere il risultato finale
        assertNotNull(result);
        assertEquals(2, result.size()); // Solo 2 studenti iniziano con "G"
        assertEquals("Giovanni", result.get(0).getNome());
        assertEquals("Giorgia", result.get(1).getNome());
        verify(studentiRepository, times(1)).findAll();
        verify(studentiMapper, times(2)).toDTO(any(Studenti.class)); // Il mapper viene chiamato 2 volte
    }

    @Test
    void testFindStudentByLetter_NotFound() {
        String letter = "z";
        List<Studenti> mockStudents = List.of(
                new Studenti(1, "Giovanni", "Rossi", "giovanni.rossi@example.com"),
                new Studenti(2, "Giorgia", "Bianchi", "giorgia.bianchi@example.com")
        );

        when(studentiRepository.findAll()).thenReturn(mockStudents);

        // Act & Assert
        // In questo caso visto che non ci sono studenti che cominciano con la z ci aspettiamo che vengs
        //lanciata un'eccezione come nel metodo
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentiService.findByLetter(letter);
        });

        // ora passiamo alle verifiche del risultato
        assertEquals("There are not name with" + letter, exception.getMessage());
        verify(studentiRepository, times(1)).findAll();
        verifyNoInteractions(studentiMapper); // verifica che il mapper non venga chiamato perche la lista è vuota e non ci sono studenti da mappare)
    }
}







