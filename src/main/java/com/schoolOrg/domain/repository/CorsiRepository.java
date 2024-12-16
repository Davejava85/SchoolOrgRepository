package com.schoolOrg.domain.repository;

import com.schoolOrg.domain.model.Corsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsiRepository extends JpaRepository<Corsi, Integer> {

    Corsi findByNomeCorso(String nomeCorso);
}