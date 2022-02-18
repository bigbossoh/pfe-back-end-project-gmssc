package com.bossoh.gmsscbackend.Dto;
import com.bossoh.gmsscbackend.entities.Preventive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PreventiveDto {
    private Long id;
    private Boolean programmer;
    private LocalDate dateProgramation;
    private Boolean isDone;
    private String typeTravaux;
    private Boolean periodique;
    private LocalDate dateDebutIntervention;
    private LocalDate dateFinIntervention;
    private Boolean resolu;
    private Boolean rapportInterv;
    private GroupeIntervenantDto groupeIntervenantDto;
    public static Preventive toEntity(PreventiveDto dto){
        if(dto==null){
            return null;
        }
        Preventive newPreventive=new Preventive();
        newPreventive.setId(dto.getId());
        newPreventive.setProgrammer(dto.getProgrammer());
        newPreventive.setTypeTravaux(dto.getTypeTravaux());
        newPreventive.setDateProgramation(dto.getDateProgramation());
        newPreventive.setIsDone(dto.getIsDone());
        newPreventive.setPeriodique(dto.getPeriodique());
        newPreventive.setDateDebutIntervention(dto.getDateDebutIntervention());
        newPreventive.setDateFinIntervention(dto.getDateFinIntervention());
        newPreventive.setResolu(dto.getResolu());
        newPreventive.setRapportInterv(dto.getRapportInterv());
        newPreventive.setGroupeIntervenant(GroupeIntervenantDto.toEntity(dto.getGroupeIntervenantDto()));
        return newPreventive;
    }
    public static PreventiveDto fromEntity(Preventive preventive){
        if(preventive==null){
            return null;
        }
        return  PreventiveDto.builder()
                .id(preventive.getId())
                .programmer(preventive.getProgrammer())
                .typeTravaux(preventive.getTypeTravaux())
                .dateProgramation(preventive.getDateProgramation())
                .isDone(preventive.getIsDone())
                .periodique(preventive.getPeriodique())
                .dateDebutIntervention(preventive.getDateDebutIntervention())
                .dateFinIntervention(preventive.getDateFinIntervention())
                .resolu(preventive.getResolu())
                .rapportInterv(preventive.getRapportInterv())
                .groupeIntervenantDto(GroupeIntervenantDto.fromEntity(preventive.getGroupeIntervenant()))
                .build();

    }
}
