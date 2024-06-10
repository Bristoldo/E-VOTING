package com.example.evotingbackend.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.evotingbackend.models.Candidature;
import com.example.evotingbackend.models.Etudiant;
import com.example.evotingbackend.models.Scrutin;
import com.example.evotingbackend.models.Vote;
import com.example.evotingbackend.models.enums.Filiere;
import com.example.evotingbackend.models.enums.Status;
import com.example.evotingbackend.models.requests.ScrutinRequest;
import com.example.evotingbackend.repository.CandidatureRepository;
import com.example.evotingbackend.repository.EtudiantRepository;
import com.example.evotingbackend.repository.ScrutinRepository;
import com.github.javafaker.Faker;

@Service
public class ScrutinService {

    private final Status[] status = Status.values();

    private final Filiere[] filieres = Filiere.values();

    private final Faker faker = new Faker();

    @Autowired
    private ScrutinRepository scrutinRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private CandidatureRepository candidatureRepository;

    public ResponseEntity<?> getAllScrutin() {
        return new ResponseEntity<>(scrutinRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> findScrutinByFiliereAndNiveau(ScrutinRequest scrutinRequest) {

        var scrutins = scrutinRepository.findByFiliereAndNiveau(scrutinRequest.getFiliere(),
                scrutinRequest.getNiveau());
        if (scrutins.isEmpty()) {
            return new ResponseEntity<>(scrutins, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(scrutins, HttpStatus.OK);
    }

    public ResponseEntity<?> findScrutin(Integer id) {
        try {
            var scrutin = scrutinRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Scrutin non trouvée"));
            return new ResponseEntity<>(scrutin, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> createScrutin(Scrutin scrutin) {

        Hibernate.initialize(scrutin.getCandidatures());
        Hibernate.initialize(scrutin.getVotes());
        scrutinRepository.save(scrutin);

        Filiere filiere = (Filiere) scrutin.getFiliere();
        int niveau = scrutin.getNiveau();

        List<Etudiant> etudiants = etudiantService.findByFiliereAndNiveau(filiere, niveau);

        for (Etudiant etudiant : etudiants) {
            etudiant.setEst_eligible(true);
            etudiantRepository.save(etudiant);
        }
        return new ResponseEntity<>("Scrutin cree avec succes", HttpStatus.OK);
    }

    public ResponseEntity<?> updateScrutin(Scrutin new_scrutin, Integer id) {
        try {
            scrutinRepository.findById(id).orElseThrow(() -> new RuntimeException("Scrutin non trouvée"));
            new_scrutin.setId(id);
            scrutinRepository.save(new_scrutin);
            return new ResponseEntity<>("Scrutin mise a jour avec succes", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> deleteScrutin(Integer id) {
        scrutinRepository.deleteById(id);
        return new ResponseEntity<>("Scrutin supprimé avec succes", HttpStatus.OK);
    }

    public Scrutin dataGeneratorScrutin() {
        int randomIndex1 = faker.random().nextInt(status.length-1);

        int randomIndex2 = faker.random().nextInt(filieres.length-1);

        // List<Candidature> candidaturesList = candidatureRepository.findAll().stream().toList();

        // Set<Candidature> candidatures = null;

        // List<Candidature> randomSubList = null;

        // if (!candidaturesList.isEmpty()) {

            // int randomIndexL1 = faker.random().nextInt((candidaturesList.size() / 2) + 1);

            // int randomIndexL2 = faker.random().nextInt((candidaturesList.size() / 2) + 1, candidaturesList.size());

        //     randomSubList = candidaturesList.subList(randomIndexL1, randomIndexL2);

        //     candidatures = randomSubList.stream().collect(Collectors.toSet());
        // }

        var scrutin = Scrutin.builder().build();
        scrutin.setTitre(faker.lorem().sentence());
        scrutin.setDate_debut(new Date(System.currentTimeMillis() + 6000));
        scrutin.setDate_fin(new Date(System.currentTimeMillis() + 6000 * 2));
        scrutin.setDelais_cand(new Date(System.currentTimeMillis() + 7000));
        scrutin.setStatus(status[randomIndex1]);
        scrutin.setFiliere(filieres[randomIndex2]);
        scrutin.setNiveau(faker.random().nextInt(1, 5));
        scrutin.setOption(null);
        Hibernate.initialize(scrutin.getCandidatures());
        scrutin.setCandidatures(new ArrayList<Candidature>());
        Hibernate.initialize(scrutin.getVotes());
        scrutin.setVotes(new ArrayList<Vote>());
        return scrutin;
    }
}
