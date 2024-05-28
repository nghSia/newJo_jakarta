package com.jpo.newjpo.modele;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "discount")
@Getter
@Setter
@AllArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Long id;

    @Column(name = "discount_code")
    private String code;

    @Column(name = "discount_val")
    private Float discountVal;

    @OneToMany(mappedBy = "discount")
    private Set<Commande> commandes = new HashSet<>();

    public Discount() {}
}
