package com.bossoh.gmsscbackend.Dto;


import com.bossoh.gmsscbackend.entities.Contrat;
import com.bossoh.gmsscbackend.entities.Equipement;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ContratDto {

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
    private boolean nettoyageEnlevementResidus;
    private boolean etablissementRapportVisite;
    private boolean risquesEventuels;
    private boolean coutInterventionsInclus;
    private boolean coutInterventionsExclus;
    private boolean coutInterventionsFacture;
    private SocieteDto societeDto;
    private List<EquipementDto> EquipementDtos;


    public static Contrat toEntity(ContratDto dto) {
        if (dto == null) {
            return null;
        }
        Contrat contrat = new Contrat();

        contrat.setId(dto.getId());
        contrat.setCodeContrat(dto.getCodeContrat());
        contrat.setDateSignature(dto.getDateSignature());
        contrat.setDureeContrat(dto.getDureeContrat());
        contrat.setDateFin(dto.getDateFin());
        contrat.setContactUrgence(dto.getContactUrgence());
        contrat.setDelaiIntervention(dto.getDelaiIntervention());
        contrat.setResiliation(dto.isResiliation());
        contrat.setVerificationControleGeneral(dto.isVerificationControleGeneral());
        contrat.setVerificationFixationsSupports(dto.isVerificationFixationsSupports());
        contrat.setVerificationEcoulementEaux(dto.isVerificationEcoulementEaux());
        contrat.setDetectionFuitesEventuelles(dto.isDetectionFuitesEventuelles());
        contrat.setVerificationCircuitFrigorifique(dto.isVerificationCircuitFrigorifique());
        contrat.setVerificationBonneMarcheSecurites(dto.isVerificationBonneMarcheSecurites());
        contrat.setNettoyageFiltre(dto.isNettoyageFiltre());
        contrat.setDepoussierageNettoyageUnites(dto.isDepoussierageNettoyageUnites());
        contrat.setDesinfectionNettoyageBacs(dto.isDesinfectionNettoyageBacs());
        contrat.setNettoyageEnlevementResidus(dto.isNettoyageEnlevementResidus());
        contrat.setEtablissementRapportVisite(dto.isEtablissementRapportVisite());
        contrat.setRisquesEventuels(dto.isRisquesEventuels());
        contrat.setCoutInterventionsFacture(dto.isCoutInterventionsFacture());
        contrat.setCoutInterventionsInclus(dto.isCoutInterventionsInclus());
        contrat.setCoutInterventionsExclus(dto.isCoutInterventionsExclus());
        contrat.setSociete(SocieteDto.toEntity(dto.getSocieteDto()));

        return contrat;
    }
    public static ContratDto fromEntity(Contrat contrat){
        if(contrat==null){
            return null;
        }
        return ContratDto.builder()
                .id(contrat.getId())
                .codeContrat(contrat.getCodeContrat())
                .dureeContrat(contrat.getDureeContrat())
                .dateFin(contrat.getDateFin())
                .contactUrgence(contrat.getContactUrgence())
                .delaiIntervention(contrat.getDelaiIntervention())
                .resiliation(contrat.isResiliation())
                .verificationControleGeneral(contrat.isVerificationControleGeneral())
                .verificationFixationsSupports(contrat.isVerificationFixationsSupports())
                .verificationCircuitFrigorifique(contrat.isVerificationCircuitFrigorifique())
                .detectionFuitesEventuelles(contrat.isDetectionFuitesEventuelles())
                .nettoyageFiltre(contrat.isNettoyageFiltre())
                .depoussierageNettoyageUnites(contrat.isDepoussierageNettoyageUnites())
                .verificationEcoulementEaux(contrat.isVerificationEcoulementEaux())
                .desinfectionNettoyageBacs(contrat.isDesinfectionNettoyageBacs())
                .nettoyageEnlevementResidus(contrat.isNettoyageEnlevementResidus())
                .etablissementRapportVisite(contrat.isEtablissementRapportVisite())
                .risquesEventuels(contrat.isRisquesEventuels())
                .coutInterventionsInclus(contrat.isCoutInterventionsInclus())
                .coutInterventionsExclus(contrat.isCoutInterventionsExclus())
                .coutInterventionsFacture(contrat.isCoutInterventionsFacture())
                .societeDto(SocieteDto.fromEntity(contrat.getSociete()))
                .build();
    }
}
