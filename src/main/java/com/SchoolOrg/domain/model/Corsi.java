package com.SchoolOrg.domain.model;

import jakarta.persistence.*;
import lombok.*;



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
}
