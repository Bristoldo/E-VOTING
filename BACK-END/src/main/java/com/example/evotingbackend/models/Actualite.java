package com.example.evotingbackend.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "actualites")
public class Actualite {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "actualites_seq")
    @SequenceGenerator(name = "actualites_seq", sequenceName = "actualites_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    String titre;

    @Column(nullable = false)
    String contenu;

    Date date_publication;

}
