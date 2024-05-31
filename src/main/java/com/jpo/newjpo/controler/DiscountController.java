package com.jpo.newjpo.controler;

import com.jpo.newjpo.DTO.DiscountDTO;
import com.jpo.newjpo.modele.Discount;
import com.jpo.newjpo.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping
    public ResponseEntity<List<Discount>> getDiscount() {
        return new ResponseEntity<>(discountService.getAlldiscounts(), HttpStatus.OK);
    }

    @GetMapping("/{p_id}")
    public ResponseEntity<Discount> getDiscountByID(@PathVariable Long p_id) {
        Discount discountAtrouver = discountService.getdiscountById(p_id);
        if(discountAtrouver != null) {
            return new ResponseEntity<>(discountService.getdiscountById(p_id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Discount> createDiscount(@RequestBody DiscountDTO p_discount) {
        Discount discountACreer = discountService.create(p_discount);
        if(discountACreer != null) {
            return new ResponseEntity<>(discountACreer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{p_id}")
    public ResponseEntity<?> mettreAJourTotalement(@PathVariable Long p_id, @RequestBody DiscountDTO p_discount) {
        try {
            boolean isUpdated = discountService.updateDiscount(p_discount, p_id);
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
    public ResponseEntity<?> mettreAJourPartiellement(@PathVariable Long p_id, @RequestBody DiscountDTO p_discount){
        try {
            boolean isUpdated = discountService.updateDiscountPartielle(p_discount, p_id);
            if (isUpdated) {
                return new ResponseEntity<>("Billet mis à jour avec succès", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Erreur lors de la mise à jour du stade", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{p_id}")
    public ResponseEntity<?> delete(@PathVariable Long p_id) {
        boolean isDeleted = discountService.delete(p_id);
        if(isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
