package com.example.evotingbackend.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.evotingbackend.models.Etudiant;
import com.example.evotingbackend.models.enums.Filiere;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

    @Query("""
              SELECT e 
              FROM Etudiant e
              WHERE e.filiere=:filiere 
              AND e.niveau=:niveau
              """)
    List<Etudiant> findByFiliereAndNiveau(Filiere filiere, int niveau);

}
