package com.example.evotingbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.evotingbackend.models.Scrutin;
import com.example.evotingbackend.repository.ScrutinRepository;

@Service
public class ScrutinService {

    @Autowired
    private ScrutinRepository scrutinRepository;

    public ResponseEntity<?> getAllScrutin() {
        return new ResponseEntity<>(scrutinRepository.findAll(), HttpStatus.OK);
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
        scrutinRepository.save(scrutin);
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

}
