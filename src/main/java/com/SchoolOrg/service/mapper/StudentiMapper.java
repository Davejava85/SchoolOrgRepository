package com.SchoolOrg.service.mapper;

import com.SchoolOrg.domain.dto.StudentiDTO;
import com.SchoolOrg.domain.model.Studenti;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentiMapper {

    StudentiMapper INSTANCE = Mappers.getMapper(StudentiMapper.class);


    StudentiDTO toDTO(Studenti studenti);

    List<StudentiDTO> toDTO(List<Studenti> studentiList);


    Studenti toEntity(StudentiDTO studentiDTO);
}
