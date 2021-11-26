package com.bossoh.gmsscbackend.Dto;

import com.bossoh.gmsscbackend.entities.Pieces;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PiecesDto {
    private Long id;
    private String codePiece;
    private String nomPiece;
    private String nomBatiment;
    private String description;
    private int capacitePiece;
    private int positionEtage;
    private String typeSalle;
    private BienImmobilierDto bienImmobilierDto;



    public static Pieces toEntity(PiecesDto dto){
        if(dto==null){
            return null;
        }
        Pieces p=new Pieces();
        p.setId(dto.getId());
        p.setCodePiece(dto.getCodePiece());
        p.setNomPiece(dto.getNomPiece());
        p.setNomBatiment(dto.getNomBatiment());
        p.setDescription(dto.getDescription());
        p.setCapacitePiece(dto.getCapacitePiece());
        p.setPositionEtage(dto.getPositionEtage());
        p.setTypeSalle(dto.getTypeSalle());
        p.setBienImmobilier(BienImmobilierDto.toEntity(dto.getBienImmobilierDto()));
        return  p;
    }
    public static PiecesDto fromEntity(Pieces pieces){
        if(pieces==null){
            return null;
        }
        return PiecesDto.builder()
                .id(pieces.getId())
                .codePiece(pieces.getCodePiece())
                .nomPiece(pieces.getNomPiece())
                .nomBatiment(pieces.getNomBatiment())
                .description(pieces.getDescription())
                .capacitePiece(pieces.getCapacitePiece())
                .positionEtage(pieces.getPositionEtage())
                .typeSalle(pieces.getTypeSalle())
                .bienImmobilierDto(BienImmobilierDto.fromEntity(pieces.getBienImmobilier()))
                .build();

    }
}
