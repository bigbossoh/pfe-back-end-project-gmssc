package com.bossoh.gmsscbackend.Dto;
import com.bossoh.gmsscbackend.entities.IntervenantGroupeIntervenant;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class IntervenantGroupeIntervenantDto {

    private Long id;
    private LocalDate dateAffectionUser;
    private GroupeIntervenantDto groupeIntervenantDto;
    private IntervenantDto intervenantDto;

    public static IntervenantGroupeIntervenant toEntity(IntervenantGroupeIntervenantDto dto){
        if(dto==null){
            return null;
        }
        IntervenantGroupeIntervenant newUserGrpInterv =new IntervenantGroupeIntervenant();
        newUserGrpInterv.setId(dto.getId());
        newUserGrpInterv.setDateAffectionUser(dto.getDateAffectionUser());
        newUserGrpInterv.setGroupeIntervenant(GroupeIntervenantDto.toEntity(dto.getGroupeIntervenantDto()));
        newUserGrpInterv.setIntervenant(IntervenantDto.toEntity(dto.getIntervenantDto()));
        return newUserGrpInterv;
    }
    public static IntervenantGroupeIntervenantDto fromEntity(IntervenantGroupeIntervenant intervenantGroupeIntervenant){

        if(intervenantGroupeIntervenant ==null){
            return null;
        }
        return IntervenantGroupeIntervenantDto.builder()
                .id(intervenantGroupeIntervenant.getId())
                .dateAffectionUser(intervenantGroupeIntervenant.getDateAffectionUser())
                .groupeIntervenantDto(GroupeIntervenantDto.fromEntity(intervenantGroupeIntervenant.getGroupeIntervenant()))
                .intervenantDto(IntervenantDto.fromEntity(intervenantGroupeIntervenant.getIntervenant()))
                .build();
    }
}
