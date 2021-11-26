package com.bossoh.gmsscbackend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table
public class Contrat extends AbstractEntity {
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
    private boolean nettoyageEnlevementResidus;
    private boolean etablissementRapportVisite;
    private boolean risquesEventuels;
    private boolean coutInterventionsInclus ;
    private boolean coutInterventionsExclus;
    private boolean coutInterventionsFacture;
    @OneToOne
    @JoinColumn(name = "societe_id", referencedColumnName = "id")
    private Societe societe;
    @OneToMany(mappedBy = "contratEquipement")
    private List<Equipement> Equipements;
}
