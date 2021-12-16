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
public class GroupeIntervenant extends AbstractEntity {
    private LocalDate dateAffectation;
    private LocalDate DateFinTravaux;
    private String commentaire;
    @ManyToOne
    private SignalerPanne signalerPanne;
}
