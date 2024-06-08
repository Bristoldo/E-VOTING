package com.example.evotingbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.evotingbackend.services.EtudiantService;

@RestController
@RequestMapping("/api/auth/etudiant")
public class EtudiantConroller {

     @Autowired
    private EtudiantService etudiantService;

    @GetMapping(value = { "/", "" })
    public ResponseEntity<?> getAllEtudiant() {
        return etudiantService.getAllEtudiant();
    }
}
