package com.schoolOrg.domain.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "iscrizioni")
@Getter
@Setter

public class Iscrizioni {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "studente_id", nullable = false)
    private Studenti studenti;

    @ManyToOne
    @JoinColumn(name = "corso_id", nullable = false)
    private Corsi corsi;

    @Column(name = "data_iscrizione")
    private LocalDateTime dataIscrizione;

}
