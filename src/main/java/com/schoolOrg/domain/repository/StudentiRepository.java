package com.schoolOrg.domain.repository;

import com.schoolOrg.domain.model.Studenti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentiRepository extends JpaRepository<Studenti, Integer> {

   // @Query(value = "SELECT * FROM studenti WHERE email = :email" , nativeQuery = true)
    Optional<Studenti> findByEmail(String email);

    List<Studenti> findByNomeAndCognome(String nome, String cognome);
}