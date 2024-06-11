package com.example.evotingbackend.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoterRequest {

    private Integer idUser;

    private Integer idCandidature;

}
