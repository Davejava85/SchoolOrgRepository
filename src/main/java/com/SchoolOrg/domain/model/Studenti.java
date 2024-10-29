package com.SchoolOrg.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "studenti")
@Getter
@Setter

public class Studenti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cognome", nullable = false, length = 100)
    private String cognome;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

}
