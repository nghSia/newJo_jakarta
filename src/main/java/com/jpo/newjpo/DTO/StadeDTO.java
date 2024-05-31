package com.jpo.newjpo.DTO;

import com.jpo.newjpo.modele.Epreuve;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class StadeDTO {
    private Long id;
    private String nom;
    private String adresse;
    private Set<Epreuve> epreuves = new HashSet<>();
}
