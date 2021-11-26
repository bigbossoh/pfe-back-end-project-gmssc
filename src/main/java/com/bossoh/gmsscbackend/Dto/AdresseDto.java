package com.bossoh.gmsscbackend.Dto;

import com.bossoh.gmsscbackend.entities.Adresse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdresseDto {

    private String pays;
    private String ville;
    private String rue;
    private String codePostale;
    private String adresse1;
    private String adresse2;

    public static AdresseDto fromEntity(Adresse adresse){
        if (adresse==null){
            return null;
        }
        return AdresseDto.builder()
                .pays(adresse.getPays())
                .ville(adresse.getVille())
                .rue(adresse.getRue())
                .codePostale(adresse.getCodePostale())
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .build();
    }
    public static Adresse toEntity(AdresseDto dto){
        if(dto==null){
            return null;
        }
        Adresse newAdresse= new Adresse();
        newAdresse.setPays(dto.getPays());
        newAdresse.setVille(dto.getVille());
        newAdresse.setRue(dto.getRue());
        newAdresse.setCodePostale(dto.getCodePostale());
        newAdresse.setAdresse1(dto.getAdresse1());
        newAdresse.setAdresse2(dto.getAdresse2());
        return  newAdresse;
    }
}
