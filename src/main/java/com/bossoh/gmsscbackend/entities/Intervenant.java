package com.bossoh.gmsscbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Intervenant extends AbstractEntity {
    private String nomInterv;
    private String prenomInterv;
    private String fonctionInterv;
    private String mobile;
    private String adresse;
    private String autreInfo;
    @ManyToOne
    private Societe societe;
}
