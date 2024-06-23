package com.example.evotingbackend.models;

import java.util.List;

import com.example.evotingbackend.models.enums.Filiere;
import com.example.evotingbackend.models.enums.Option;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
    @Enumerated(EnumType.STRING)
    private Filiere filiere;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Option option;

    @Column(nullable = false)
    private boolean est_eligible;
    
    @JsonIgnore
    @OneToMany(mappedBy = "etudiant", fetch = FetchType.EAGER)
    private List<Candidature> candidatures;
}