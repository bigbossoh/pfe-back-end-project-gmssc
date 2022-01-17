package com.bossoh.gmsscbackend.Dto;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.Intervenant;
import com.bossoh.gmsscbackend.entities.Societe;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntervenantDto {
    private Long id;
    private String nomInterv;
    private String prenomInterv;
    private String fonctionInterv;
    private String mobile;
    private String adresse;
    private String autreInfo;
    private SocieteDto societeDto;
    public static Intervenant toEntity(IntervenantDto dto) {
        if (dto == null) {
            return null;
        }
        Intervenant newIntervenant = new Intervenant();
        newIntervenant.setId(dto.getId());
        newIntervenant.setNomInterv(dto.getNomInterv());
        newIntervenant.setPrenomInterv(dto.getPrenomInterv());
        newIntervenant.setFonctionInterv(dto.getFonctionInterv());
        newIntervenant.setMobile(dto.getMobile());
        newIntervenant.setAdresse(dto.getAdresse());
        newIntervenant.setAutreInfo(dto.getAutreInfo());
        newIntervenant.setSociete(SocieteDto.toEntity(dto.getSocieteDto()));
        return newIntervenant;
    }
    public static IntervenantDto fromEntity(Intervenant intervenant) {
        if (intervenant == null) {
            return null;
        }
        return IntervenantDto.builder()
                .id(intervenant.getId())
                .nomInterv(intervenant.getNomInterv())
                .prenomInterv(intervenant.getPrenomInterv())
                .fonctionInterv(intervenant.getFonctionInterv())
                .mobile(intervenant.getMobile())
                .adresse(intervenant.getAdresse())
                .autreInfo(intervenant.getAutreInfo())
                .societeDto(SocieteDto.fromEntity(intervenant.getSociete()))
                .build();
    }
}
