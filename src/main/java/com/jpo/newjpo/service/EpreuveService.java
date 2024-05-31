package com.jpo.newjpo.service;

import com.jpo.newjpo.DTO.EpreuveDTO;
import com.jpo.newjpo.DTO.StadeDTO;
import com.jpo.newjpo.enums.EpreuveStatus;
import com.jpo.newjpo.modele.Discount;
import com.jpo.newjpo.modele.Epreuve;
import com.jpo.newjpo.modele.Stade;
import com.jpo.newjpo.repository.EpreuveRepository;
import com.jpo.newjpo.repository.StadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpreuveService {
    private final EpreuveRepository epreuveRepository;
    private final StadeRepository stadeRepository;

    @Autowired
    public EpreuveService(EpreuveRepository p_epreuveRepository, StadeRepository p_stadeRepository) {
        this.epreuveRepository = p_epreuveRepository;
        this.stadeRepository = p_stadeRepository;
    }

    public List<Epreuve> findAll(){
        return epreuveRepository.findAll();
    }

    public Epreuve findById(Long p_id) {
        return epreuveRepository.findById(p_id).orElse(null);
    }

    public Epreuve save(EpreuveDTO p_epreuve) {
        Epreuve epreuveNew = new Epreuve();
        epreuveNew.setNom(p_epreuve.getNom());
        epreuveNew.setStatus(EpreuveStatus.A_VENIR);

        return epreuveRepository.save(epreuveNew);
    }

    public boolean deleteById(Long p_id) {
        Epreuve epreuveASupp = epreuveRepository.findById(p_id).orElse(null);
        if (epreuveASupp != null) {
            epreuveRepository.delete(epreuveASupp);
            return true;
        }
        return false;
    }

    public boolean updateEpreuve(EpreuveDTO p_epreuve, Long p_id) {
        Epreuve epreuveAModifier = epreuveRepository.findById(p_id).orElseThrow(() -> new IllegalArgumentException(
                "epreuve non trouve"
        ));

        if(p_epreuve.getNom() == null){
            throw new IllegalArgumentException("Informations manquants");
        }

        epreuveAModifier.setNom(p_epreuve.getNom());
        epreuveAModifier.setStatus(p_epreuve.getStatus());
        epreuveRepository.save(epreuveAModifier);
        return true;
    }

    public boolean updateEpreuvePartielle(EpreuveDTO p_epreuve, Long p_id) {
        Epreuve epreuveAModifier = epreuveRepository.findById(p_id).orElseThrow(() -> new IllegalArgumentException(
                "Epreuve non trouve"
        ));
        if(p_epreuve.getNom() != null && !p_epreuve.getNom().equals("")) {
            epreuveAModifier.setNom(p_epreuve.getNom());
        }
        if(p_epreuve.getStatus() != null && !p_epreuve.getStatus().equals("")) {
            epreuveAModifier.setStatus(p_epreuve.getStatus());
        }
        epreuveRepository.save(epreuveAModifier);
        return true;
    }

    public Epreuve setStade(Long p_epreuveId, Long p_stadeId) {
        Stade stadeAModifier = stadeRepository.findById(p_stadeId).orElseThrow(() -> new
                IllegalArgumentException("Stade non trouve"));
        Epreuve epreuveAAjouter = epreuveRepository.findById(p_epreuveId).orElseThrow(() -> new
                IllegalArgumentException("Epreuve non trouve"));

        boolean epreuveConflict = stadeAModifier.getEpreuves().stream()
                .anyMatch(e -> e.getStatus() == EpreuveStatus.A_VENIR || e.getStatus() == EpreuveStatus.EN_COURS);
        if (epreuveConflict) {
            throw new IllegalArgumentException("Une epreuve est deja prevu dans ce stade");
        }

        epreuveAAjouter.setStade(stadeAModifier);
        stadeAModifier.getEpreuves().add(epreuveAAjouter);

        epreuveRepository.save(epreuveAAjouter);
        stadeRepository.save(stadeAModifier);
        return epreuveAAjouter;
    }
}
