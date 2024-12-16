package com.schoolOrg.domain.dto;

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
   //  @JsonProperty("nome")
    private String nome;
  //  @JsonProperty("cognome")
    private String cognome;
  //  @JsonProperty("email")
    private String email;



}