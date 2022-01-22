package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.Dto.IntervenantDto;
import com.bossoh.gmsscbackend.Dto.PieceEquipementDto;

import java.util.List;

public interface PieceEquipementService {

    List<PieceEquipementDto> listOfPieceEquipement();

    PieceEquipementDto savePieceEquipement(PieceEquipementDto pedto);

    PieceEquipementDto getPieceEquipementyId(Long id);

    List<PieceEquipementDto> listOfPieceEquipementByEquipementDtos(Long IdEquipement);

    boolean deletePieceEquipement(Long id);
}
