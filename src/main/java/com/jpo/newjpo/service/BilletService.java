package com.jpo.newjpo.service;

import com.jpo.newjpo.DTO.BilletDTO;
import com.jpo.newjpo.modele.Billet;
import com.jpo.newjpo.modele.Epreuve;
import com.jpo.newjpo.repository.BilletRepository;
import com.jpo.newjpo.repository.EpreuveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilletService {
    private final BilletRepository billetRepository;
    private final EpreuveRepository epreuveRepository;

    @Autowired
    public BilletService(EpreuveRepository p_epreuveRepository, BilletRepository p_billetRepository) {
        this.epreuveRepository = p_epreuveRepository;
        this.billetRepository = p_billetRepository;
    }

    public List<Billet> findAll(){
        return billetRepository.findAll();
    }

    public Billet findById(Long p_id) {
        return billetRepository.findById(p_id).orElse(null);
    }

    public Billet save(BilletDTO p_billet) {
        Billet billetnew = new Billet();
        billetnew.setNom(p_billet.getNom());
        billetnew.setPrix(p_billet.getPrix());

        return billetRepository.save(billetnew);
    }

    public boolean deleteById(Long p_id) {
        Billet billetASupp = billetRepository.findById(p_id).orElse(null);
        if (billetASupp != null) {
            billetRepository.delete(billetASupp);
            return true;
        }
        return false;
    }

    public boolean updateBillet(BilletDTO p_billet, Long p_id) {
        Billet billetAModifier = billetRepository.findById(p_id).orElseThrow(() -> new IllegalArgumentException(
                "epreuve non trouve"
        ));

        if(p_billet.getNom() == null){
            throw new IllegalArgumentException("Informations manquants");
        }

        billetAModifier.setNom(p_billet.getNom());
        billetAModifier.setPrix(p_billet.getPrix());
        billetRepository.save(billetAModifier);
        return true;
    }

    public boolean updateBilletPartielle(BilletDTO p_billet, Long p_id) {
        Billet billetAModifier = billetRepository.findById(p_id).orElseThrow(() -> new IllegalArgumentException(
                "Epreuve non trouve"
        ));
        if(p_billet.getNom() != null && !p_billet.getNom().equals("")) {
            billetAModifier.setNom(p_billet.getNom());
        }
        if(p_billet.getPrix() != null && !p_billet.getPrix().equals("")) {
            billetAModifier.setPrix(p_billet.getPrix());
        }
        billetRepository.save(billetAModifier);
        return true;
    }

    public Billet setEpreuve(Long p_billetId, Long p_epreuveId) {
        Billet billetAAjouter = billetRepository.findById(p_billetId).orElseThrow(() -> new
                IllegalArgumentException("Stade non trouve"));
        Epreuve epreuveAmodifier = epreuveRepository.findById(p_epreuveId).orElseThrow(() -> new
                IllegalArgumentException("Epreuve non trouve"));

        boolean epreuveConflict = epreuveAmodifier.getBillets().stream()
                .anyMatch(e -> e.getNom() == billetAAjouter.getNom());
        if (epreuveConflict) {
            throw new IllegalArgumentException("Ce billet existe deja pour cette epreuve");
        }

        billetAAjouter.setEpreuve(epreuveAmodifier);
        epreuveAmodifier.getBillets().add(billetAAjouter);

        billetRepository.save(billetAAjouter);
        epreuveRepository.save(epreuveAmodifier);
        return billetAAjouter;
    }
}
