package com.bossoh.gmsscbackend.Dto;


import com.bossoh.gmsscbackend.entities.Corrective;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class CorrectiveDto {
    private Long id;
    private String actionsMener;
    private Boolean reparation;
    private Boolean depannage;
    private String typeTravaux;
    private double coutMaintenance;
    private LocalDate dateDebutIntervention;
    private LocalDate dateFinIntervention;
    private Boolean resolu;
    private Boolean rapportInterv;
    private GroupeIntervenantDto groupeIntervenantDto;
    public static Corrective toEntity(CorrectiveDto dto){
        if(dto==null){
            return null;
        }
        Corrective newCorrective=new Corrective();
        newCorrective.setId(dto.getId());
        newCorrective.setActionsMener(dto.getActionsMener());
        newCorrective.setReparation(dto.getReparation());
        newCorrective.setTypeTravaux(dto.getTypeTravaux());
        newCorrective.setDepannage(dto.getDepannage());
        newCorrective.setCoutMaintenance(dto.getCoutMaintenance());
        newCorrective.setDateDebutIntervention(dto.getDateDebutIntervention());
        newCorrective.setDateFinIntervention(dto.getDateFinIntervention());
        newCorrective.setResolu(dto.getResolu());
        newCorrective.setRapportInterv(dto.getRapportInterv());
        newCorrective.setGroupeIntervenant(GroupeIntervenantDto.toEntity(dto.getGroupeIntervenantDto()));
        return newCorrective;
    }
    public static CorrectiveDto fromEntity(Corrective corrective){
        if(corrective==null){
            return null;
        }
        return  CorrectiveDto.builder()
                .id(corrective.getId())
                .actionsMener(corrective.getActionsMener())
                .reparation(corrective.getReparation())
                .typeTravaux(corrective.getTypeTravaux())
                .depannage(corrective.getDepannage())
                .coutMaintenance(corrective.getCoutMaintenance())
                .dateDebutIntervention(corrective.getDateDebutIntervention())
                .dateFinIntervention(corrective.getDateFinIntervention())
                .resolu(corrective.getResolu())
                .rapportInterv(corrective.getRapportInterv())
                .groupeIntervenantDto(GroupeIntervenantDto.fromEntity(corrective.getGroupeIntervenant()))
                .build();

    }
}
