package com.schoolOrg.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "corsi")
    @Getter
    @Setter
    public class Corsi {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(name = "nome_corso", nullable = false, length = 100)
        private String nomeCorso;

        @Column(name = "descrizione")
        private String descrizione;

        @OneToMany(mappedBy = "corsi", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Iscrizioni> iscrizioni = new ArrayList<>();
}
