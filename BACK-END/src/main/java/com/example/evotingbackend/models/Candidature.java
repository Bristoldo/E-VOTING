package com.example.evotingbackend.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "candidatures")
public class Candidature {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "candidatures_seq")
    @SequenceGenerator(name = "candidatures_seq", sequenceName = "candidatures_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private Date date_postuler;

    @Column(nullable = false)
    private Integer nombre_voix;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "scrutin_id")
    private Scrutin scrutin;
}
