package com.example.evotingbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.evotingbackend.models.Actualite;
import com.example.evotingbackend.repository.ActualiteRepository;

@Service
public class ActualiteService {

    @Autowired
    private ActualiteRepository actualiteRepository;

    public ResponseEntity<?> getAllActualite() {
        return new ResponseEntity<>(actualiteRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> findActualite(Integer id) {
     
        try {
            var actualite = actualiteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Actualité non trouvée"));
            return new ResponseEntity<>(actualite, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<?> createActualite(Actualite actualite) {
        actualiteRepository.save(actualite);
        return new ResponseEntity<>("Actualité cree avec succes", HttpStatus.OK);
    }

    public ResponseEntity<?> updateActualite(Actualite new_actualite, Integer id) {

        try {
            actualiteRepository.findById(id).orElseThrow(() -> new RuntimeException("Actualité non trouvée"));
            new_actualite.setId(id);
            actualiteRepository.save(new_actualite);

            return new ResponseEntity<>("Actualité mise a jour avec succes", HttpStatus.OK);

        } catch (RuntimeException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }
    
    public ResponseEntity<?> deleteActualite(Integer id) {
        actualiteRepository.deleteById(id);
        return new ResponseEntity<>("Actualité supprimé avec succes", HttpStatus.OK);
    }

}
