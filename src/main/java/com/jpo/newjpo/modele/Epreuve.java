package com.jpo.newjpo.modele;

import com.jpo.newjpo.enums.EpreuveStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "epreuve")
@Data
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "epreuve_id")
    private Long id;

    @Column(name = "epreuve_nom")
    private String nom;

    @Enumerated(EnumType.STRING)
    @Column(name = "epreuve_status")
    private EpreuveStatus status;

    @ManyToOne
    @JoinColumn(name = "stade_id")
    private Stade stade;

    @OneToMany(mappedBy = "epreuve")
    private Set<Billet> billets = new HashSet<>();
}
