package com.bossoh.gmsscbackend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipement extends AbstractEntity {

    private String codeEquipement;
    private String marque;
    private String numeroSerie;
    private String modele;
    private double hauteur;
    private double largeur;
    private double Longueur;
    private double poidsNetInterieur;
    private double poidsNetExterieur;
    private String puissance;
    private LocalDate dateAchat;
    private Integer garantie;
    private String nomFournisseur;
    private String personneRessource;
    private String telephone;
    private String mobile;
    private boolean livreAvecAccessoires;
    private  String description;

    private String numFacture;
    private String prixFacture;
    private LocalDate dateMiseService;
    private String etatService;
    private String typeClim;
    @OneToMany(mappedBy = "equipement")
    private List<PieceEquipement> pieceEquipements;
    @ManyToOne
    @JoinColumn(name="id_contrat")
    private Contrat contratEquipement;
    @ManyToOne
    private Societe societe;
}
