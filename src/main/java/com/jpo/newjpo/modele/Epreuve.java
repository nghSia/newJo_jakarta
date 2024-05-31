package com.jpo.newjpo.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpo.newjpo.enums.EpreuveStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Stade stade;

    @OneToMany(mappedBy = "epreuve", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<Billet> billets = new HashSet<>();
}
