package com.jpo.newjpo.DTO;

import com.jpo.newjpo.enums.EpreuveStatus;
import com.jpo.newjpo.modele.Billet;
import com.jpo.newjpo.modele.Stade;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class EpreuveDTO {
    private Long id;

    private String nom;

    private EpreuveStatus status;

    private Stade stade;

    private Set<Billet> billets = new HashSet<>();
}
