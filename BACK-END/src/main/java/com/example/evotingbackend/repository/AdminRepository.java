package com.example.evotingbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.evotingbackend.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
