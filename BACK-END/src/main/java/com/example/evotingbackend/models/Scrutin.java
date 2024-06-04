package com.example.evotingbackend.models;

import java.util.Date;
import java.util.List;

import com.example.evotingbackend.models.enums.Filiere;
import com.example.evotingbackend.models.enums.Option;
import com.example.evotingbackend.models.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scrutins_seq")
    @SequenceGenerator(name = "scrutins_seq", sequenceName = "scrutins_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private Date date_debut;

    private Date date_fin;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Filiere filiere;

    @Column(nullable = false)
    private Integer niveau;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private Option option;

    @Column(nullable = false)
    private Date delais_cand;

    @OneToMany(mappedBy = "scrutin", fetch = FetchType.EAGER)
    private List<Candidature> candidatures;
   
    @JsonIgnore
    @OneToMany(mappedBy = "scrutin", fetch = FetchType.EAGER)
    private List<Vote> votes;
}
