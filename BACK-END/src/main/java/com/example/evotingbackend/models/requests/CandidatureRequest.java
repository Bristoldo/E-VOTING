package com.example.evotingbackend.models.requests;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidatureRequest {
    
    private Integer id;

    private Date date_postuler;
    
    private Integer nombre_voix;

    private Integer etudiant_id;

    private Integer scrutin_id;

}
