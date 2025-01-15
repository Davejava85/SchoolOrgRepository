package com.schoolOrg.domain.repository;

import com.schoolOrg.domain.model.Professori;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessoriRepository extends JpaRepository <Professori,Integer> {

    Professori findByEmail(String email);


}
