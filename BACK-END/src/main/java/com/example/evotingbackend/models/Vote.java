package com.example.evotingbackend.models;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Cette classe permet d'identifier un l'etudiant qui a vote mais ceci n'est plus tres necessaire 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "votes")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "votes_seq")
    @SequenceGenerator(name = "votes_seq", sequenceName = "votes_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private Date date_vote;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Scrutin scrutin;

}
