package com.schoolOrg.service.mapper;

import com.schoolOrg.domain.dto.ProfessoriDTO;
import com.schoolOrg.domain.model.Professori;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ProfessoriMapper {

    ProfessoriDTO toDTO(Professori professori);

    Professori toEntity(ProfessoriDTO professoriDTO);
}
