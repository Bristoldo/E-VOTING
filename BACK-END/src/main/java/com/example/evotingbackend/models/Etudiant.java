package com.example.evotingbackend.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "etudiantBuilder")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "etudiants")
public class Etudiant extends User {
    
    @Column(nullable = true, unique = true)
    private String matricule;
    
    @Column(nullable = false)
    private Integer niveau;

    @Column(nullable = false)
    private Filiere filiere;

    @Column(nullable = true)
    private Option option;

    @OneToMany(mappedBy = "etudiant")
    private Set <Candidature> candidatures;

}
