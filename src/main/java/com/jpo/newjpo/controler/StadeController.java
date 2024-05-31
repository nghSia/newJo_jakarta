package com.jpo.newjpo.controler;

import com.jpo.newjpo.DTO.StadeDTO;
import com.jpo.newjpo.modele.Stade;
import com.jpo.newjpo.service.StadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/stade")
public class StadeController {
    private final StadeService stadeService;

    @Autowired
    public StadeController(StadeService stadeService) {
        this.stadeService = stadeService;
    }

    @GetMapping
    public ResponseEntity<List<Stade>> getStades() {
        return new ResponseEntity<>(stadeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{p_id}")
    public ResponseEntity<Stade> getStadeByID(@PathVariable Long p_id) {
        Stade stadeATrouver = stadeService.findById(p_id);
        if(stadeATrouver != null) {
            return new ResponseEntity<>(stadeService.findById(p_id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Stade> createStade(@RequestBody StadeDTO p_stade) {
        Stade stadeACreer = stadeService.save(p_stade);
        if(stadeACreer != null) {
            return new ResponseEntity<>(stadeACreer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{p_id}")
    public ResponseEntity<?> mettreAJourTotalement(@PathVariable Long p_id, @RequestBody StadeDTO p_stade) {
        try {
            boolean isUpdated = stadeService.updateStade(p_stade, p_id);
            if (isUpdated) {
                return new ResponseEntity<>("Stade mis à jour avec succès", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Erreur lors de la mise à jour du stade", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{p_id}")
    public ResponseEntity<?> mettreAJourPartiellement(@PathVariable Long p_id, @RequestBody StadeDTO p_stade){
        try {
            boolean isUpdated = stadeService.updateStadePartielle(p_stade, p_id);
            if (isUpdated) {
                return new ResponseEntity<>("Stade mis à jour avec succès", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Erreur lors de la mise à jour du stade", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{p_id}")
    public ResponseEntity<?> delete(@PathVariable Long p_id) {
        boolean isDeleted = stadeService.deleteById(p_id);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
