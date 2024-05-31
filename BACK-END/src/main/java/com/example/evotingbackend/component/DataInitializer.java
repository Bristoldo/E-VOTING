package com.example.evotingbackend.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.evotingbackend.repository.EtudiantRepository;
import com.example.evotingbackend.services.EtudiantService;

@Component
public class DataInitializer implements CommandLineRunner {

    public final int numberEtudiants = 10;
    public final int numberCandidature = 50;
    public final int number = 50;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Override
    public void run(String... args) throws Exception {
        generateEtudiant(numberEtudiants);
    }

    public void generateEtudiant(int numberEtudiants) {
        for (int i = 0; i < numberEtudiants; i++) {
            var etudiant = etudiantService.dataGeneratorEtudiant();
            etudiantRepository.save(etudiant);
        }
    }
}
