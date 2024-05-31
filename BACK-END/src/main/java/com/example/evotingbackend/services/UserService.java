package com.example.evotingbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.evotingbackend.repository.UserRepository;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> getUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> findUserById(Integer id) {
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }
    
}
