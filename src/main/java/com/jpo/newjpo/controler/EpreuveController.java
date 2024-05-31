package com.jpo.newjpo.controler;

import com.jpo.newjpo.DTO.EpreuveDTO;
import com.jpo.newjpo.modele.Epreuve;
import com.jpo.newjpo.service.EpreuveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epreuve")
public class EpreuveController {
    private final EpreuveService epreuveService;

    @Autowired
    public EpreuveController(EpreuveService epreuveService) {
        this.epreuveService = epreuveService;
    }

    @GetMapping
    public ResponseEntity<List<Epreuve>> getEpreuve() {
        return new ResponseEntity<>(epreuveService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{p_id}")
    public ResponseEntity<Epreuve> getEpreuveByID(@PathVariable Long p_id) {
        Epreuve EpreuveATrouver = epreuveService.findById(p_id);
        if(EpreuveATrouver != null) {
            return new ResponseEntity<>(epreuveService.findById(p_id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Epreuve> createEpreuve(@RequestBody EpreuveDTO p_epreuve) {
        Epreuve epreuveACreer = epreuveService.save(p_epreuve);
        if(epreuveACreer != null) {
            return new ResponseEntity<>(epreuveACreer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{p_id}")
    public ResponseEntity<?> mettreAJourTotalement(@PathVariable Long p_id, @RequestBody EpreuveDTO p_epreuve) {
        try {
            boolean isUpdated = epreuveService.updateEpreuve(p_epreuve, p_id);
            if (isUpdated) {
                return new ResponseEntity<>("Epreuve mis à jour avec succès", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Erreur lors de la mise à jour du stade", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{p_id}")
    public ResponseEntity<?> mettreAJourPartiellement(@PathVariable Long p_id, @RequestBody EpreuveDTO p_epreuve){
        try {
            boolean isUpdated = epreuveService.updateEpreuvePartielle(p_epreuve, p_id);
            if (isUpdated) {
                return new ResponseEntity<>("Epreuve mis à jour avec succès", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Erreur lors de la mise à jour du stade", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{epreuveId}/set-stade/{stadeId}")
    public ResponseEntity<?> setStade(@PathVariable Long epreuveId, @PathVariable Long stadeId) {
        try {
            epreuveService.setStade(epreuveId, stadeId);
            return new ResponseEntity<>("Epreuve added successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while adding the epreuve", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{p_id}")
    public ResponseEntity<?> delete(@PathVariable Long p_id) {
        boolean isDeleted = epreuveService.deleteById(p_id);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
