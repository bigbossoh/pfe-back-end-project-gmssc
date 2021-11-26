package com.bossoh.gmsscbackend.Dto;

import com.bossoh.gmsscbackend.entities.PieceEquipement;
import lombok.Builder;
import lombok.Data;



import java.time.LocalDate;

@Data
@Builder
public class PieceEquipementDto {

    private Long id;
    private LocalDate dateInstallation;
    private EquipementDto equipementDto;
    private PiecesDto piecesDto;

    public static PieceEquipement toEntity(PieceEquipementDto dto){
        if(dto==null){
            return null;
        }
        PieceEquipement newPeqt =new PieceEquipement();
        newPeqt.setId(dto.getId());
        newPeqt.setDateInstallation(dto.getDateInstallation());
        newPeqt.setEquipement(EquipementDto.toEntity(dto.getEquipementDto()));
        newPeqt.setPieces(PiecesDto.toEntity(dto.getPiecesDto()));
        return newPeqt;
    }
    public static PieceEquipementDto fromEntity(PieceEquipement pieceEquipement){

        if(pieceEquipement==null){
            return null;
        }
        return PieceEquipementDto.builder()
                .id(pieceEquipement.getId())
                .dateInstallation(pieceEquipement.getDateInstallation())
                .equipementDto(EquipementDto.fromEntity(pieceEquipement.getEquipement()))
                .piecesDto(PiecesDto.fromEntity(pieceEquipement.getPieces()))
                .build();
    }
}
