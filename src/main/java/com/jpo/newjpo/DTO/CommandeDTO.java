package com.jpo.newjpo.DTO;

import com.jpo.newjpo.enums.TypeCommande;
import com.jpo.newjpo.modele.Billet;
import com.jpo.newjpo.modele.Discount;
import com.jpo.newjpo.modele.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class CommandeDTO {
    private Long id;
    private LocalDateTime dateCommande;
    private Float montant;
    private TypeCommande type;
    private Long userId;
    private List<BilletCommande> billets;
    private Long discountid;
}
