package com.jpo.newjpo.repository;

import com.jpo.newjpo.modele.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
}
