package com.jpo.newjpo.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<Commande> commandes = new HashSet<>();

    public Discount() {}
}
