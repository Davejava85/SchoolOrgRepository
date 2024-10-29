package com.SchoolOrg.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

public class StudentiDTO
{
   // @JsonProperty("id")
    private int id;
   // @JsonProperty("nome")
    private String nome;
   // @JsonProperty("cognome")
    private String cognome;
    //@JsonProperty("email")
    private String email;

// public  StudentiDTO() {
// }

}