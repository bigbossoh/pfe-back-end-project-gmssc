package com.bossoh.gmsscbackend.Dto;

import com.bossoh.gmsscbackend.entities.BienImmobilier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BienImmobilierDto {
    private Long id;
    private String codeBienImmobilier;
    private String nomBienImmobilier;
    private String typeBienImmobilier;
    private Integer nbreBatiments;
    private String Adresses1;
    private String Adresses2;
    private String mobile;
    private String telephone;

    private String villeSociete;
    private String commune;
    private String quartier;

    private String autreInformation;
    private Integer nombrePiece;
    private SocieteDto societeDto;
    public static BienImmobilier toEntity(BienImmobilierDto dto){
       if(dto==null){
           return null;
       }
       BienImmobilier newBien=new BienImmobilier();
       newBien.setId(dto.getId());
       newBien.setCodeBienImmobilier(dto.getCodeBienImmobilier());
       newBien.setNomBienImmobilier(dto.getNomBienImmobilier());
       newBien.setTypeBienImmobilier(dto.getTypeBienImmobilier());
        newBien.setNbreBatiments(dto.getNbreBatiments());
        newBien.setAdresses1(dto.getAdresses1());
        newBien.setAdresses2(dto.getAdresses2());
        newBien.setMobile(dto.getMobile());

        newBien.setVilleSociete(dto.getVilleSociete());
        newBien.setQuartier(dto.getQuartier());
        newBien.setCommune(dto.getCommune());

        newBien.setTelephone(dto.getTelephone());
        newBien.setAutreInformation(dto.getAutreInformation());
        newBien.setNombrePiece(dto.getNombrePiece());
        newBien.setSociete(SocieteDto.toEntity(dto.getSocieteDto()));
        return newBien;
    }
    public static BienImmobilierDto fromEntity(BienImmobilier bienImmobilier){
    if(bienImmobilier==null){
        return null;
    }
        return  BienImmobilierDto.builder()
                .id(bienImmobilier.getId())
                .codeBienImmobilier(bienImmobilier.getCodeBienImmobilier())
                .nomBienImmobilier(bienImmobilier.getNomBienImmobilier())
                .typeBienImmobilier(bienImmobilier.getTypeBienImmobilier())
                .nbreBatiments(bienImmobilier.getNbreBatiments())
                .Adresses1(bienImmobilier.getAdresses1())
                .Adresses2(bienImmobilier.getAdresses2())
                .mobile(bienImmobilier.getMobile())

                .villeSociete(bienImmobilier.getVilleSociete())
                .commune(bienImmobilier.getCommune())
                .quartier(bienImmobilier.getQuartier())

                .telephone(bienImmobilier.getTelephone())
                .autreInformation(bienImmobilier.getAutreInformation())
                .nombrePiece(bienImmobilier.getNombrePiece())
                .societeDto(SocieteDto.fromEntity(bienImmobilier.getSociete()))
                .build();

    }
}
