package com.jpo.newjpo.modele;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "billet")
@Getter
@Setter
@AllArgsConstructor
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "billet_id")
    private Long id;
    @Column(name = "billet_nom")
    private String nom;
    @Column(name = "billet_prix")
    private Float prix;
    @ManyToOne
    @JoinColumn(name = "commande_id")
    private Commande commande;

    @ManyToOne
    @JoinColumn(name = "epreuve_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Epreuve epreuve;

    public Billet() {

    }
}
