package com.example.evotingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.evotingbackend.models.Scrutin;


public interface ScrutinRepository extends JpaRepository<Scrutin, Integer>{

}
