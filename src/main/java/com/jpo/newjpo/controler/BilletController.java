package com.jpo.newjpo.controler;

import com.jpo.newjpo.DTO.BilletDTO;
import com.jpo.newjpo.modele.Billet;
import com.jpo.newjpo.service.BilletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billet")
public class BilletController {
    private final BilletService billetService;

    @Autowired
    public BilletController(BilletService billetService) {
        this.billetService = billetService;
    }

    @GetMapping
    public ResponseEntity<List<Billet>> getBillet() {
        return new ResponseEntity<>(billetService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{p_id}")
    public ResponseEntity<Billet> getBilletByID(@PathVariable Long p_id) {
        Billet billetAtrouver = billetService.findById(p_id);
        if(billetAtrouver != null) {
            return new ResponseEntity<>(billetService.findById(p_id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Billet> createBillet(@RequestBody BilletDTO p_billet) {
        Billet billetACreer = billetService.save(p_billet);
        if(billetACreer != null) {
            return new ResponseEntity<>(billetACreer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{p_id}")
    public ResponseEntity<?> mettreAJourTotalement(@PathVariable Long p_id, @RequestBody BilletDTO p_billet) {
        try {
            boolean isUpdated = billetService.updateBillet(p_billet, p_id);
            if (isUpdated) {
                return new ResponseEntity<>("Billet mis à jour avec succès", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Erreur lors de la mise à jour du stade", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{p_id}")
    public ResponseEntity<?> mettreAJourPartiellement(@PathVariable Long p_id, @RequestBody BilletDTO p_billet){
        try {
            boolean isUpdated = billetService.updateBilletPartielle(p_billet, p_id);
            if (isUpdated) {
                return new ResponseEntity<>("Billet mis à jour avec succès", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Erreur lors de la mise à jour du stade", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{billetId}/set-epreuve/{epreuveId}")
    public ResponseEntity<?> setStade(@PathVariable Long billetId, @PathVariable Long epreuveId) {
        try {
            billetService.setEpreuve(billetId, epreuveId);
            return new ResponseEntity<>("Epreuve added successfully", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while adding the epreuve", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{p_id}")
    public ResponseEntity<?> delete(@PathVariable Long p_id) {
        boolean isDeleted = billetService.deleteById(p_id);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
