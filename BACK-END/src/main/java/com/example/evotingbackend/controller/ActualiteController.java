package com.example.evotingbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evotingbackend.models.Actualite;
import com.example.evotingbackend.services.ActualiteService;

@RestController
@RequestMapping("/api/auth/actualite")
public class ActualiteController {

    @Autowired
    private ActualiteService actualiteService;

    @GetMapping(value = { "/", "" })
    public ResponseEntity<?> getAllActualite() {
        return actualiteService.getAllActualite();
    }

    @GetMapping(value = { "/{id}", "{id}" })
    public ResponseEntity<?> findActualite(@PathVariable Integer id) {
        return actualiteService.findActualite(id);
    }

    @PostMapping(value = { "/", "" })
    public ResponseEntity<?> createActualite(@RequestBody Actualite actualite) {
        return actualiteService.createActualite(actualite);
    }

    @PutMapping(value = { "/{id}", "{id}" })
    public ResponseEntity<?> updateActualite(@RequestBody Actualite new_actualite, @PathVariable Integer id) {
        return actualiteService.updateActualite(new_actualite, id);
    }

    @DeleteMapping(value = { "/{id}", "{id}" })
    public ResponseEntity<?> deleteActualite(@PathVariable Integer id) {
        return actualiteService.deleteActualite(id);
    }

}
