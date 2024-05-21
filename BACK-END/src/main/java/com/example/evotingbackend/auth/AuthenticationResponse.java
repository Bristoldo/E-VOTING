package com.example.evotingbackend.auth;


import com.example.evotingbackend.models.Filiere;
import com.example.evotingbackend.models.Option;
import com.example.evotingbackend.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;

    private String firstname;

    private String lastname;

    private Integer niveau;

    private Filiere filiere;

    private Option option;

    private Role role;

}
