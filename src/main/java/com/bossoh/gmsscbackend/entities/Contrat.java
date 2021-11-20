package com.bossoh.gmsscbackend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table
public class Contrat {
    @Id
    @GeneratedValue
    private Long id;
    private String codeContrat;
    private LocalDate dateSignature;
    private int dureeContrat;
    private LocalDate dateFin;
    private String contactUrgence;
    private String delaiIntervention;
    private boolean resiliation;
    private boolean verificationControleGeneral;
    private boolean verificationFixationsSupports;
    private boolean verificationCircuitFrigorifique;
    private boolean detectionFuitesEventuelles;
    private boolean verificationBonneMarcheSecurites;
    private boolean nettoyageFiltre;
    private boolean depoussierageNettoyageUnites;
    private boolean verificationEcoulementEaux;
    private boolean desinfectionNettoyageBacs;
    private boolean nettoyageEnlèvementResidus;
    private boolean etablissementRapportVisite;
    private boolean risquesEventuels;
    private boolean coutInterventionsInclus ;
    private boolean coutInterventionsExclus;
    private boolean coutInterventionsFacture;
    @OneToOne(mappedBy = "contrat")
    private Societe societe;
    @OneToMany(mappedBy = "contrat")
    private List<Equipement> listEquipement;
}
