package com.bossoh.gmsscbackend.Dto;
import com.bossoh.gmsscbackend.entities.UtilisateurGroupeIntervenant;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class UtilisateurGroupeIntervenantDto {

    private Long id;
    private LocalDate dateAffectionUser;
    private GroupeIntervenantDto groupeIntervenantDto;
    private UtilisateurDto utilisateurDto;

    public static UtilisateurGroupeIntervenant toEntity(UtilisateurGroupeIntervenantDto dto){
        if(dto==null){
            return null;
        }
        UtilisateurGroupeIntervenant newUserGrpInterv =new UtilisateurGroupeIntervenant();
        newUserGrpInterv.setId(dto.getId());
        newUserGrpInterv.setDateAffectionUser(dto.getDateAffectionUser());
        newUserGrpInterv.setGroupeIntervenant(GroupeIntervenantDto.toEntity(dto.getGroupeIntervenantDto()));
        newUserGrpInterv.setUtilisateur(UtilisateurDto.toEntity(dto.getUtilisateurDto()));
        return newUserGrpInterv;
    }
    public static UtilisateurGroupeIntervenantDto fromEntity(UtilisateurGroupeIntervenant utilisateurGroupeIntervenant){

        if(utilisateurGroupeIntervenant==null){
            return null;
        }
        return UtilisateurGroupeIntervenantDto.builder()
                .id(utilisateurGroupeIntervenant.getId())
                .dateAffectionUser(utilisateurGroupeIntervenant.getDateAffectionUser())
                .groupeIntervenantDto(GroupeIntervenantDto.fromEntity(utilisateurGroupeIntervenant.getGroupeIntervenant()))
                .utilisateurDto(UtilisateurDto.fromEntity(utilisateurGroupeIntervenant.getUtilisateur()))
                .build();
    }
}
