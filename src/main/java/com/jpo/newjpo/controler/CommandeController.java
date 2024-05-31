package com.jpo.newjpo.controler;

import com.jpo.newjpo.DTO.CommandeDTO;
import com.jpo.newjpo.modele.Commande;
import com.jpo.newjpo.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {

    private final CommandeService commandeService;

    @Autowired
    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public ResponseEntity<List<Commande>> getAllCommandes() {
        return new ResponseEntity<>(commandeService.getAllCommande(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommandeById(@PathVariable Long p_id) {
        Commande commandeATrouver = commandeService.getCommandeByID(p_id);
        if (commandeATrouver != null) {
            return new ResponseEntity<>(commandeATrouver, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Commande> createCommande(@RequestBody CommandeDTO p_commande) {
        try {
            Commande newCommande = commandeService.create(p_commande);
            return new ResponseEntity<>(newCommande, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCommande(@PathVariable Long p_id) {
        boolean isDeleted = commandeService.delete(p_id);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
