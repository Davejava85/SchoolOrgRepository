package com.schoolOrg.service.mapper;


import com.schoolOrg.domain.dto.IscrizioniDTO;
import com.schoolOrg.domain.dto.StudentiDTO;
import com.schoolOrg.domain.model.Iscrizioni;
import com.schoolOrg.domain.model.Studenti;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring" )
public interface IscrizioniMapper {

    IscrizioniDTO toDTO(Iscrizioni iscrizioni);

    //List<StudentiDTO> toDTOList(List<Studenti> studentiList);
    Iscrizioni toEntity(IscrizioniDTO iscrizioniDTO);

    List <IscrizioniDTO> toDTOList (List <Iscrizioni> iscrizioniList);

}

