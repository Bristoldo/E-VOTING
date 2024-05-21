package com.example.evotingbackend.models;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scrutins")
public class Scrutin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Date date_debut;
    
    @Column(nullable = false)
    private Date date_fin;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String filiere;

    @Column(nullable = false)
    private Integer niveau;

    @Column(nullable = true)
    private Option option;

    @Column(nullable = false)
    private Date delais_cand;

    @OneToMany(mappedBy = "scrutin")
    private Set <Candidature> candidatures;

    @OneToMany(mappedBy = "scrutin")
    private Set <Vote> votes;
}
