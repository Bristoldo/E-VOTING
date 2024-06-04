package com.example.evotingbackend.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.evotingbackend.repository.CandidatureRepository;
import com.example.evotingbackend.repository.EtudiantRepository;
import com.example.evotingbackend.repository.ScrutinRepository;
import com.example.evotingbackend.services.CandidatureService;
import com.example.evotingbackend.services.EtudiantService;
import com.example.evotingbackend.services.ScrutinService;

@Component
public class DataInitializer implements CommandLineRunner {

    public final int numberEtudiants = 20;

    public final int numberCandidature = 15;

    public final int numberScrutins = 5;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ScrutinService scrutinService;

    @Autowired
    private ScrutinRepository scrutinRepository;

    @Autowired
    private CandidatureService candidatureService;

    @Autowired
    private CandidatureRepository candidatureRepository;

    @Override
    public void run(String... args) throws Exception {
        generateEtudiant(numberEtudiants);
        generateScrutin(numberScrutins);
        generateCandidature(numberCandidature);
    }

    public void generateEtudiant(int numberEtudiants) {

        for (int i = 0; i < numberEtudiants; i++) {
            var etudiant = etudiantService.dataGeneratorEtudiant();
            etudiantRepository.save(etudiant);
        }
    }

    public void generateScrutin(int numberScrutins) {
        for (int i = 0; i < numberScrutins; i++) {
            var scrutin = scrutinService.dataGeneratorScrutin();
            scrutinRepository.save(scrutin);
        }
    }

    public void generateCandidature(int numberCandidature) {
        for (int i = 0; i < numberCandidature; i++) {
            var candidature = candidatureService.dataGeneratorCandidature();
            candidatureRepository.save(candidature);
        }
    }
}
