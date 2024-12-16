package com.schoolOrg.service.mapper;



import com.schoolOrg.domain.dto.StudentiDTO;
import com.schoolOrg.domain.model.Studenti;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentiMapper {

   // StudentiMapper INSTANCE = Mappers.getMapper(StudentiMapper.class);

  StudentiDTO toDTO(Studenti studenti);




   List<StudentiDTO> toDTOList(List<Studenti> studentiList);


    Studenti toEntity(StudentiDTO studentiDTO);
}
