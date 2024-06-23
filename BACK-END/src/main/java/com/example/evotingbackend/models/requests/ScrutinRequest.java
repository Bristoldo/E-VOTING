package com.example.evotingbackend.models.requests;

import com.example.evotingbackend.models.enums.Filiere;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrutinRequest {

    private Filiere filiere;

    private int niveau;
}
