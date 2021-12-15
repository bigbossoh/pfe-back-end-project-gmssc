package com.bossoh.gmsscbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SignalerPanne extends AbstractEntity {
    private String codeSignalerPanne;
    private String objetPanne;
    private String descriptionPanne;
    private LocalDate dateSignalerPanne;
    private LocalDate dateResolutionPanne;
    private LocalDate dateAnnulation;
    private String etatSignalerPanne;
    private String causeAnnulation;
    @ManyToOne
    private Utilisateur utilisateur;
    @ManyToOne
    private Pieces pieces;
}
