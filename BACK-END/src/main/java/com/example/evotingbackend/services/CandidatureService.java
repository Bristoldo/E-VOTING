package com.example.evotingbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.evotingbackend.models.Candidature;
import com.example.evotingbackend.models.Etudiant;
import com.example.evotingbackend.models.requests.CandidatureRequest;
import com.example.evotingbackend.repository.CandidatureRepository;
import com.example.evotingbackend.repository.EtudiantRepository;
import com.example.evotingbackend.repository.ScrutinRepository;

@Service
public class CandidatureService {

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
                .nombre_voix(candidatureRequest.getNombre_voix())
                .etudiant(etudiant)
                .scrutin(scrutin)
                .build();
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

}
