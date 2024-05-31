package com.jpo.newjpo.modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpo.newjpo.enums.TypeCommande;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name= "commande")
@Getter
@Setter
@AllArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commande_id")
    private Long id;

    @Column(name = "commande_date")
    private LocalDateTime dateCommande;

    private Float montant;

    @Enumerated(EnumType.STRING)
    @Column(name = "commande_type")
    private TypeCommande type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Set<Billet> billets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "discount_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Discount discount;

    public Commande() {}

    public Commande(TypeCommande type) {
        this.dateCommande = LocalDateTime.now();
        this.type = type;
    }
}

