package com.example.evotingbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evotingbackend.models.requests.CandidatureRequest;
import com.example.evotingbackend.models.requests.VoterRequest;
import com.example.evotingbackend.services.CandidatureService;

@RestController
@RequestMapping("/api/auth/candidature")
@CrossOrigin
public class CandidatureController {

    @Autowired
    private CandidatureService candidatureService;

    @GetMapping(value = { "/", "" })
    public ResponseEntity<?> getAllCandidature() {
        return candidatureService.getAllCandidature();
    }

    @GetMapping(value = { "/{id}", "/{id}/" })
    public ResponseEntity<?> findCandidature(@PathVariable Integer id) {
        return candidatureService.findCandidature(id);
    }

    @PostMapping(value = { "/", "" })
    public ResponseEntity<?> createCandidature(@RequestBody CandidatureRequest candidature) {
        return candidatureService.createCandidature(candidature);
    }

    @PutMapping(value = { "/{id}", "/{id}/" })
    public ResponseEntity<?> updateCandidature(@RequestBody CandidatureRequest new_candidatureRequest,
            @PathVariable Integer id) {
        return candidatureService.updateCandidature(new_candidatureRequest, id);
    }

    @DeleteMapping(value = { "/{id}", "/{id}/" })
    public ResponseEntity<?> deleteCandidature(@PathVariable Integer id) {
        return candidatureService.deleteCandidature(id);
    }

    @PostMapping(value = { "/voter", "/voter/" })
    public ResponseEntity<?> voterCandidature(@RequestBody VoterRequest voterRequest) {
        return candidatureService.voterCandidature(voterRequest);
    }

}
