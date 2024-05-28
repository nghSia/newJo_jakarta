package com.jpo.newjpo.modele;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stade")
@Data
public class Stade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stade_id")
    private Long id;

    @Column(name = "stade_nom")
    private String nom;

    @Column(name = "stade_adresse")
    private String adresse;

    @OneToMany(mappedBy = "stade")
    private Set<Epreuve> epreuves = new HashSet<>();
}
