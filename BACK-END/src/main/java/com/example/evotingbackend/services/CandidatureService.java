package com.example.evotingbackend.services;

import java.util.Date;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.evotingbackend.models.Candidature;
import com.example.evotingbackend.models.requests.CandidatureRequest;
import com.example.evotingbackend.models.requests.VoterRequest;
import com.example.evotingbackend.repository.CandidatureRepository;
import com.example.evotingbackend.repository.EtudiantRepository;
import com.example.evotingbackend.repository.ScrutinRepository;
import com.github.javafaker.Faker;

@Service
public class CandidatureService {

    private final Faker faker = new Faker();

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ScrutinRepository scrutinRepository;

    public ResponseEntity<?> getAllCandidature() {
        return new ResponseEntity<>(candidatureRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> findCandidature(Integer id) {

        try {
            var candidature = candidatureRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Candidature non trouvée"));
            return new ResponseEntity<>(candidature, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> createCandidature(CandidatureRequest candidatureRequest) {

        var etudiant = etudiantRepository.findById(candidatureRequest.getEtudiant_id()).orElse(null);

        var scrutin = scrutinRepository.findById(candidatureRequest.getScrutin_id()).orElse(null);

        var candidature = Candidature.builder()
                .date_postuler(candidatureRequest.getDate_postuler())
                .nombre_voix(0)
                .etudiant(etudiant)
                .scrutin(scrutin)
                .build();

        var ajout = scrutin.getCandidatures().add(candidature);

        if (ajout) {
            scrutinRepository.save(scrutin);
        }
        candidatureRepository.save(candidature);

        return new ResponseEntity<>("Candidature cree avec succes", HttpStatus.OK);
    }

    public ResponseEntity<?> updateCandidature(CandidatureRequest new_candidatureRequest, Integer id) {

        try {
            candidatureRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidature non trouvée"));

            var etudiant = etudiantRepository.findById(new_candidatureRequest.getEtudiant_id()).orElse(null);

            var scrutin = scrutinRepository.findById(new_candidatureRequest.getScrutin_id()).orElse(null);

            var candidature = Candidature.builder()
                    .id(id)
                    .date_postuler(new_candidatureRequest.getDate_postuler())
                    .nombre_voix(new_candidatureRequest.getNombre_voix())
                    .etudiant(etudiant)
                    .scrutin(scrutin)
                    .build();
            candidatureRepository.save(candidature);

            return new ResponseEntity<>("Candidature mise a jour avec succes", HttpStatus.OK);

        } catch (RuntimeException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

    public ResponseEntity<?> deleteCandidature(Integer id) {
        candidatureRepository.deleteById(id);
        return new ResponseEntity<>("Candidature supprimé avec succes", HttpStatus.OK);
    }

    public ResponseEntity<?> voterCandidature(VoterRequest voterRequest) {

        try {
            var candidature = candidatureRepository.findById(voterRequest.getIdCandidature())
                    .orElseThrow(() -> new RuntimeException("Candidature non trouvée"));
            candidature.setNombre_voix(candidature.getNombre_voix() + 1);

            var etudiant = etudiantRepository.findById(voterRequest.getIdUser()).orElse(null);
            etudiant.setEst_eligible(false);

            etudiantRepository.save(etudiant);
            candidatureRepository.save(candidature);
            return new ResponseEntity<>("Vote effectué avec succes", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public Candidature dataGeneratorCandidature() {

        var etudiants = etudiantRepository.findAll().stream().toList();
        var scrutins = scrutinRepository.findAll().stream().toList();

        int randomIndex1 = faker.random().nextInt(etudiants.size()-1);
        int randomIndex2 = faker.random().nextInt(scrutins.size()-1);

        var etudiant_choisi = etudiants.get(randomIndex1);
        var scrutin_choisi = scrutins.get(randomIndex2);

        var candidature = Candidature.builder()
        .date_postuler(new Date(System.currentTimeMillis() + faker.random().nextInt(100, 1000)))
        .nombre_voix(0)
        .etudiant(etudiant_choisi)
        .scrutin(scrutin_choisi)
        .build();

        var candidatures_EC = etudiant_choisi.getCandidatures();
        candidatures_EC.add(candidature);
        Hibernate.initialize(etudiant_choisi.getCandidatures());
        etudiant_choisi.setCandidatures(candidatures_EC);
        etudiantRepository.save(etudiant_choisi);
        
        var candidatures_SC = scrutin_choisi.getCandidatures();
        candidatures_SC.add(candidature);
        Hibernate.initialize(scrutin_choisi.getCandidatures());
        scrutin_choisi.setCandidatures(candidatures_SC);
        scrutinRepository.save(scrutin_choisi);
        return candidature;
    }
}
