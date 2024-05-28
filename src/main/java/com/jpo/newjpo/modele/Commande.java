package com.jpo.newjpo.modele;

import com.jpo.newjpo.enums.TypeCommande;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
    private User user;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private Set<Billet> billets = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    public Commande() {}

    public Commande(TypeCommande type) {
        this.dateCommande = LocalDateTime.now();
        this.type = type;
    }
}

