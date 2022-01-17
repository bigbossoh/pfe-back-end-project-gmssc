package com.bossoh.gmsscbackend.Dto;

import com.bossoh.gmsscbackend.entities.Competence;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompetenceDto {
    private Long id;
    private String descrition;
    private String job;
    private IntervenantDto intervenantDto;
    public static Competence toEntity(CompetenceDto dto){

        if(dto==null){
            return null;
        }
        Competence cpt= new Competence();
        cpt.setId(dto.getId());
        cpt.setDescrition(dto.getDescrition());
        cpt.setJob(dto.getJob());
        cpt.setIntervenant(IntervenantDto.toEntity(dto.getIntervenantDto()));
        return cpt;
    }
    public static CompetenceDto fromEntity(Competence competence){
        if(competence==null){
            return null;
        }
        return CompetenceDto.builder()
                .id(competence.getId())
                .descrition(competence.getDescrition())
                .job(competence.getJob())
                .intervenantDto(IntervenantDto.fromEntity(competence.getIntervenant()))
                .build();
    }
}
