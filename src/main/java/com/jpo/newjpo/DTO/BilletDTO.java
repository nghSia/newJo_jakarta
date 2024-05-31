package com.jpo.newjpo.DTO;

import com.jpo.newjpo.modele.Commande;
import com.jpo.newjpo.modele.Epreuve;
import lombok.Data;

@Data
public class BilletDTO {
    private Long id;
    private String nom;
    private Float prix;
    private Commande commande;
    private Epreuve epreuve;
}
