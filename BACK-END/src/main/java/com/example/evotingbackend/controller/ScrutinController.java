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

import com.example.evotingbackend.models.Scrutin;
import com.example.evotingbackend.models.requests.ScrutinRequest;
import com.example.evotingbackend.services.ScrutinService;

@RestController
@RequestMapping("/api/auth/scrutin")
@CrossOrigin
public class ScrutinController {

    @Autowired
    private ScrutinService scrutinService;

    @GetMapping(value = { "/", ""})
    public ResponseEntity<?> getAllScrutin() {
        return scrutinService.getAllScrutin();
    }

    @PostMapping(value = { "/filiere-niveau", "filiere-niveau" })
    public ResponseEntity<?> findScrutinByFiliereAndNiveau(@RequestBody ScrutinRequest scrutinRequest) {
        return scrutinService.findScrutinByFiliereAndNiveau(scrutinRequest);
    }

    @GetMapping(value = { "/{id}", "{id}" })
    public ResponseEntity<?> findScrutin(@PathVariable Integer id) {
        return scrutinService.findScrutin(id);
    }

    @PostMapping(value = { "/", "" })
    public ResponseEntity<?> createScrutin(@RequestBody Scrutin scrutin) {
        return scrutinService.createScrutin(scrutin);
    }

    @PutMapping(value = { "/{id}", "{id}" })
    public ResponseEntity<?> updateScrutin(@RequestBody Scrutin new_scrutin, @PathVariable Integer id) {
        return scrutinService.updateScrutin(new_scrutin, id);
    }

    @DeleteMapping(value = { "/{id}", "{id}" })
    public ResponseEntity<?> deleteScrutin(@PathVariable Integer id) {
        return scrutinService.deleteScrutin(id);
    }

}
