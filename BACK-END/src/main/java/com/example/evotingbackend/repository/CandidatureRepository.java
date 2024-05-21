package com.example.evotingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.evotingbackend.models.Candidature;


public interface CandidatureRepository extends  JpaRepository<Candidature, Integer> {

}
