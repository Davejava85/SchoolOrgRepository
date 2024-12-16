package com.schoolOrg.service;

import com.schoolOrg.domain.dto.IscrizioniDTO;
import com.schoolOrg.domain.model.Iscrizioni;
import com.schoolOrg.domain.repository.IscrizioniRepository;
import com.schoolOrg.exception.ResourceNotFoundException;
import com.schoolOrg.service.mapper.IscrizioniMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IscrizioniService {

    @Autowired
    public IscrizioniRepository iscrizioniRepository;

    @Autowired
    public IscrizioniMapper iscrizioniMapper;


    public List<IscrizioniDTO> findIscrizioniPerStudent(int studenteId) {
        List<Iscrizioni> iscrizioniList = iscrizioniRepository.findByStudentiId(studenteId);
        // List <IscrizioniDTO> iscrizioniDTOList = iscrizioniMapper.toDTOList(iscrizioniList);
        if (iscrizioniList.isEmpty()) {
            throw new ResourceNotFoundException("Non ci sono iscrizioni per questo studenteId o non esiste" + studenteId);
        }
//        return iscrizioniDTOList;
        return iscrizioniList.stream()
                .map(iscrizioniMapper::toDTO)
                .collect(Collectors.toList());


    }
}
