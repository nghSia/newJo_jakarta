package com.jpo.newjpo.service;

import com.jpo.newjpo.DTO.DiscountDTO;
import com.jpo.newjpo.modele.Discount;
import com.jpo.newjpo.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    private final DiscountRepository repository;

    @Autowired
    public DiscountService(DiscountRepository repository) {
        this.repository = repository;
    }

    public List<Discount> getAlldiscounts() {
        return repository.findAll();
    }

    public Discount getdiscountById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Discount create(DiscountDTO p_discount) {
        Discount discountnew = new Discount();
        discountnew.setCode(p_discount.getCode());
        discountnew.setDiscountVal(p_discount.getDiscountVal());
        return repository.save(discountnew);
    }

    public boolean delete(Long id) {
        Discount discountASupp = repository.findById(id).orElse(null);
        if (discountASupp != null) {
            repository.delete(discountASupp);
            return true;
        }
        return false;
    }

    public boolean updateDiscount(DiscountDTO p_discount, Long p_id) {
        Discount discountAModifier = repository.findById(p_id).orElseThrow(() -> new IllegalArgumentException(
                "discount non trouve"
        ));

        if(p_discount.getCode() == null || p_discount.getDiscountVal() == null){
            throw new IllegalArgumentException("Informations manquants");
        }

        discountAModifier.setCode(p_discount.getCode());
        discountAModifier.setDiscountVal(p_discount.getDiscountVal());
        repository.save(discountAModifier);
        return true;
    }

    public boolean updateDiscountPartielle(DiscountDTO p_discount, Long p_id) {
        Discount discountAModifier = repository.findById(p_id).orElseThrow(() -> new IllegalArgumentException(
                "discount non trouve"
        ));
        if(p_discount.getCode() != null && !p_discount.getCode().equals("")) {
            discountAModifier.setCode(p_discount.getCode());
        }
        if(p_discount.getDiscountVal() != null && !p_discount.getDiscountVal().equals("")) {
            discountAModifier.setDiscountVal(p_discount.getDiscountVal());
        }
        repository.save(discountAModifier);
        return true;
    }
}
