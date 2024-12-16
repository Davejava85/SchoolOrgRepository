package com.schoolOrg.domain.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class IscrizioniDTO {

    private int id;
    private int studenteId;
    private int corsoId;
    private LocalDateTime dataIscrizione;
}
