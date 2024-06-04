package com.example.evotingbackend.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.evotingbackend.models.Candidature;
import com.example.evotingbackend.models.Etudiant;
import com.example.evotingbackend.models.enums.Filiere;
import com.example.evotingbackend.models.enums.Role;
import com.example.evotingbackend.repository.EtudiantRepository;
import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtudiantService {

    private final Filiere[] filieres = Filiere.values();

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EtudiantRepository etudiantRepository;

    private final Faker faker = new Faker();

    public ResponseEntity<?> getAllEtudiant() {
        return new ResponseEntity<>(etudiantRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> findEtudiant(Integer id) {

        try {
            var etudiant = etudiantRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Etudiant non trouvée"));
            return new ResponseEntity<>(etudiant, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> createEtudiant(Etudiant etudiant) {
        etudiantRepository.save(etudiant);
        return new ResponseEntity<>("Etudiant cree avec succes", HttpStatus.OK);
    }

    public ResponseEntity<?> updateEtudiant(Etudiant new_etudiant, Integer id) {

        try {
            etudiantRepository.findById(id).orElseThrow(() -> new RuntimeException("Etudiant non trouvée"));
            new_etudiant.setId(id);
            etudiantRepository.save(new_etudiant);

            return new ResponseEntity<>("Etudiant mise a jour avec succes", HttpStatus.OK);

        } catch (RuntimeException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

    public ResponseEntity<?> deleteEtudiant(Integer id) {
        etudiantRepository.deleteById(id);
        return new ResponseEntity<>("Etudiant supprimé avec succes", HttpStatus.OK);
    }

    public List<Etudiant> findByFiliereAndNiveau(Filiere filiere, int niveau) {
        return etudiantRepository.findByFiliereAndNiveau(filiere, niveau);
    }

    public Etudiant dataGeneratorEtudiant() {
        int randomIndex = faker.random().nextInt(filieres.length);
        int annee = faker.random().nextInt(10, 24);

        int numId = faker.random().nextInt(0000, 9999);

        String matricule = "CM-UDS-" + annee + "SCI" + numId;

        var etudiant = Etudiant.etudiantBuilder()
                .build();
        etudiant.setFirstname(faker.name().firstName());
        etudiant.setLastname(faker.name().lastName());
        etudiant.setEmail(faker.internet().emailAddress());
        etudiant.setPassword(passwordEncoder.encode("12345"));
        etudiant.setRole(Role.USER);
        etudiant.setMatricule(matricule);
        etudiant.setNiveau(faker.random().nextInt(1, 5));
        etudiant.setFiliere(filieres[randomIndex]);
        etudiant.setEst_eligible(false);
 
        // for (Candidature cand : candidatures) {
        //     Candidature candidature = Candidature.builder()
        //                             .date_postuler(cand.getDate_postuler())
        //                             .etudiant(etudiant)
        //                             .build();
        //     candidatures.add(candidature);
        // }
        Hibernate.initialize(etudiant.getCandidatures());
        etudiant.setCandidatures(new ArrayList<Candidature>());
        return etudiant;
    }
}
