package com.jpo.newjpo.repository;

import com.jpo.newjpo.modele.Billet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BilletRepository extends JpaRepository<Billet, Long> {
}
