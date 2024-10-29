package com.SchoolOrg.domain.repository;

import com.SchoolOrg.domain.model.Corsi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorsiRepository extends JpaRepository<Corsi, Integer> {

    Corsi findByNomeCorso(String nomeCorso);
}