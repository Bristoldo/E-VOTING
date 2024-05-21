package com.example.evotingbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.evotingbackend.models.Etudiant;
import com.example.evotingbackend.repository.EtudiantRepository;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

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

}
