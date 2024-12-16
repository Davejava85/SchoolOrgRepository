package com.schoolOrg.service.mapper;



import com.schoolOrg.domain.dto.CorsiDTO;
import com.schoolOrg.domain.model.Corsi;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CorsiMapper {

   CorsiMapper INSTANCE = Mappers.getMapper(CorsiMapper.class);


    CorsiDTO toDTO(Corsi corsi);


    Corsi toEntity(CorsiDTO corsiDTO);
}
