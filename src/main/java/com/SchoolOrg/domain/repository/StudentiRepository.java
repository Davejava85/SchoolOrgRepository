package com.SchoolOrg.domain.repository;

import com.SchoolOrg.domain.model.Studenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentiRepository extends JpaRepository<Studenti, Integer> {
    Studenti findByEmail(String email);
}