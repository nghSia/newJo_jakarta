package com.jpo.newjpo.service;

import com.jpo.newjpo.DTO.BilletCommande;
import com.jpo.newjpo.DTO.CommandeDTO;
import com.jpo.newjpo.modele.Billet;
import com.jpo.newjpo.modele.Commande;
import com.jpo.newjpo.modele.Discount;
import com.jpo.newjpo.modele.User;
import com.jpo.newjpo.repository.BilletRepository;
import com.jpo.newjpo.repository.CommandeRepository;
import com.jpo.newjpo.repository.DiscountRepository;
import com.jpo.newjpo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CommandeService {
    private final CommandeRepository commandeRepository;
    private final BilletRepository billetRepository;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;

    @Autowired
    public CommandeService(CommandeRepository commandeRepository, BilletRepository billetRepository,
                           UserRepository userRepository, DiscountRepository discountRepository) {
        this.commandeRepository = commandeRepository;
        this.billetRepository = billetRepository;
        this.userRepository = userRepository;
        this.discountRepository = discountRepository;
    }

    public List<Commande> getAllCommande() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeByID(Long id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public Commande create(CommandeDTO p_commande) {
        Commande cmdACreer = new Commande();
        cmdACreer.setType(p_commande.getType());
        cmdACreer.setDateCommande(LocalDateTime.now());

        User usercmd = userRepository.findById(p_commande.getUserId()).orElse(null);
        cmdACreer.setUser(usercmd);

        Set<Billet> billets = new HashSet<>();
        float totalMontant = 0.0f;

        for (BilletCommande billetcmd : p_commande.getBillets()) {
            Billet billet = billetRepository.findById(billetcmd.getBilletId()).orElseThrow();
            for (int i = 0; i < billetcmd.getQuantity(); i++) {
                Billet billetCopy = new Billet();
                billetCopy.setNom(billet.getNom());
                billetCopy.setPrix(billet.getPrix());
                billetCopy.setEpreuve(billet.getEpreuve());
                billetCopy.setCommande(cmdACreer);
                billets.add(billetCopy);
                totalMontant += billet.getPrix();
            }
        }

        cmdACreer.setBillets(billets);

        if (p_commande.getDiscountid() != null) {
            Discount discountcmd = discountRepository.findById(p_commande.getDiscountid()).orElseThrow();
            float discount = discountcmd.getDiscountVal();
            totalMontant = totalMontant * (1 - discount / 100);
            cmdACreer.setDiscount(discountcmd);
        }

        cmdACreer.setMontant(totalMontant);

        return commandeRepository.save(cmdACreer);
    }

    public boolean delete(Long id) {
        Commande cmdASupprimer = commandeRepository.findById(id).orElse(null);
        if (cmdASupprimer != null) {
            commandeRepository.delete(cmdASupprimer);
            return true;
        }
        return false;
    }
}


