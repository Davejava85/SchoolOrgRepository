package com.schoolOrg.domain.repository;

import com.schoolOrg.domain.model.Iscrizioni;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IscrizioniRepository  extends JpaRepository <Iscrizioni, Integer> {

   List <Iscrizioni> findByStudentiId (int studentiId);

    Iscrizioni findByCorsiId (int corsiId);


}
