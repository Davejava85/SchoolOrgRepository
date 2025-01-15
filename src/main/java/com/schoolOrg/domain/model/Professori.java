package com.schoolOrg.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "professori")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Professori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cognome", nullable = false, length = 100)
    private String cognome;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String ruolo;

    @OneToMany(mappedBy = "professori", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Corsi> corsi = new ArrayList<>();

    // Getter e Setter
}