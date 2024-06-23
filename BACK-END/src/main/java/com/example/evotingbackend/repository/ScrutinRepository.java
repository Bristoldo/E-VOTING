package com.example.evotingbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.evotingbackend.models.Scrutin;
import com.example.evotingbackend.models.enums.Filiere;


public interface ScrutinRepository extends JpaRepository<Scrutin, Integer>{

    @Query("""
              SELECT s 
              FROM Scrutin s
              WHERE s.filiere=:filiere 
              AND s.niveau=:niveau
              """)
    List<Scrutin> findByFiliereAndNiveau(Filiere filiere, int niveau);
}
