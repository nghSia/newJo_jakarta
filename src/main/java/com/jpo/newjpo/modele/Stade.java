package com.jpo.newjpo.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @OneToMany(mappedBy = "stade", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<Epreuve> epreuves = new HashSet<>();
}
