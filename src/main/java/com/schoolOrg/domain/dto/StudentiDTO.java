package com.schoolOrg.domain.dto;

import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class StudentiDTO
{

    private int id;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private String ruolo;

}