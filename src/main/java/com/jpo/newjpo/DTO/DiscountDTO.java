package com.jpo.newjpo.DTO;

import com.jpo.newjpo.modele.Commande;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DiscountDTO {
    private Long id;
    private String code;
    private Float discountVal;
    private Set<Commande> commandes = new HashSet<>();
}
