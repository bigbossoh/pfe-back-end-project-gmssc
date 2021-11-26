package com.bossoh.gmsscbackend.Dto;

import com.bossoh.gmsscbackend.entities.Equipement;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class EquipementDto {
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
    private Instant dateAchat;
    private Integer garantie;
    private String nomFournisseur;
    private String personneRessource;
    private String telephone;
    private String mobile;
    private boolean livreAvecAccessoires;
    private String description;
    private String typeClim;
    private ContratDto contratDto;

    public static EquipementDto fromEntity(Equipement eqpt) {
        if (eqpt == null) {
            return null;
        }
        return EquipementDto.builder()
                .id(eqpt.getId())
                .codeEquipement(eqpt.getCodeEquipement())
                .marque(eqpt.getMarque())
                .numeroSerie(eqpt.getNumeroSerie())
                .modele(eqpt.getModele())
                .hauteur(eqpt.getHauteur())
                .largeur(eqpt.getLargeur())
                .Longueur(eqpt.getLongueur())
                .poidsNetInterieur(eqpt.getPoidsNetInterieur())
                .poidsNetExterieur(eqpt.getPoidsNetExterieur())
                .puissance(eqpt.getPuissance())
                .dateAchat(eqpt.getDateAchat())
                .garantie(eqpt.getGarantie())
                .nomFournisseur(eqpt.getNomFournisseur())
                .personneRessource(eqpt.getPersonneRessource())
                .telephone(eqpt.getTelephone())
                .mobile(eqpt.getMobile())
                .livreAvecAccessoires(eqpt.isLivreAvecAccessoires())
                .description(eqpt.getDescription())
                .typeClim(eqpt.getTypeClim())
                .contratDto(ContratDto.fromEntity(eqpt.getContratEquipement()))
                .build();
    }
    public static  Equipement toEntity(EquipementDto dto){
        if(dto==null){
            return null;
        }
        Equipement eqpt= new Equipement();

        eqpt.setId(dto.getId());
        eqpt.setCodeEquipement(dto.getCodeEquipement());
        eqpt.setMarque(dto.getMarque());
        eqpt.setNumeroSerie(dto.getNumeroSerie());
        eqpt.setModele(dto.getModele());
        eqpt.setHauteur(dto.getHauteur());
        eqpt.setLongueur(dto.getLongueur());
        eqpt.setPoidsNetInterieur(dto.getPoidsNetInterieur());
        eqpt.setPoidsNetExterieur(dto.getPoidsNetExterieur());
        eqpt.setPuissance(dto.getPuissance());
        eqpt.setDateAchat(dto.getDateAchat());
        eqpt.setGarantie(dto.getGarantie());
        eqpt.setNomFournisseur(dto.getNomFournisseur());
        eqpt.setPersonneRessource(dto.getPersonneRessource());
        eqpt.setTelephone(dto.getTelephone());
        eqpt.setMobile(dto.getMobile());
        eqpt.setLivreAvecAccessoires(dto.isLivreAvecAccessoires());
        eqpt.setDescription(dto.getDescription());
        eqpt.setTypeClim(dto.getTypeClim());
        eqpt.setContratEquipement(ContratDto.toEntity(dto.getContratDto()));
        return eqpt;
    }
}

