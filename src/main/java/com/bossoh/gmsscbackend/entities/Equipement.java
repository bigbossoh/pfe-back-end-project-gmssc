package com.bossoh.gmsscbackend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Equipement {
    @Id
    @GeneratedValue
    private Long id;
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
    private String typeClim;
    @OneToMany(mappedBy = "equipement")
    private List<PieceEquipement> pieceEquipements;
}
