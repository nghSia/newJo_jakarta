package com.jpo.newjpo.service;

import com.jpo.newjpo.DTO.StadeDTO;
import com.jpo.newjpo.modele.Epreuve;
import com.jpo.newjpo.modele.Stade;
import com.jpo.newjpo.repository.EpreuveRepository;
import com.jpo.newjpo.repository.StadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StadeService {
    private final StadeRepository stadeRepository;
    private final EpreuveRepository epreuveRepository;

    @Autowired
    public StadeService(StadeRepository p_stadeRepository, EpreuveRepository p_epreuveRepository) {
        this.stadeRepository = p_stadeRepository;
        this.epreuveRepository = p_epreuveRepository;
    }

    public List<Stade> findAll(){
        return stadeRepository.findAll();
    }

    public Stade findById(Long p_id) {
        return stadeRepository.findById(p_id).orElse(null);
    }

    public Stade save(StadeDTO p_stade) {
        Stade stadeNew = new Stade();
        stadeNew.setNom(p_stade.getNom());
        stadeNew.setAdresse(p_stade.getAdresse());

        return stadeRepository.save(stadeNew);
    }

    public boolean deleteById(Long p_id) {
        Stade stadeASupp = stadeRepository.findById(p_id).orElse(null);
        if (stadeASupp != null) {
            stadeRepository.delete(stadeASupp);
            return true;
        }
        return false;
    }

    public boolean updateStade(StadeDTO p_stade, Long p_id) {
        Stade stadeAModifier = stadeRepository.findById(p_id).orElseThrow(() -> new IllegalArgumentException(
                "Stade non trouve"
        ));

        if(p_stade.getNom() == null || p_stade.getAdresse() == null){
            throw new IllegalArgumentException("Informations manquants");
        }

        stadeAModifier.setNom(p_stade.getNom());
        stadeAModifier.setAdresse(p_stade.getAdresse());
        stadeRepository.save(stadeAModifier);
        return true;
    }

    public boolean updateStadePartielle(StadeDTO p_stade, Long p_id) {
        Stade stadeAModifier = stadeRepository.findById(p_id).orElseThrow(() -> new IllegalArgumentException(
                "Stade non trouve"
        ));
        if(p_stade.getNom() != null && !p_stade.getNom().equals("")) {
            stadeAModifier.setNom(p_stade.getNom());
        }
        if(p_stade.getAdresse() != null && !p_stade.getAdresse().equals("")) {
            stadeAModifier.setAdresse(p_stade.getAdresse());
        }
        stadeRepository.save(stadeAModifier);
        return true;
    }
}
