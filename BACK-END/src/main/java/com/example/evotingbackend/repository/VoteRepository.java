package com.example.evotingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.evotingbackend.models.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer>{


}
