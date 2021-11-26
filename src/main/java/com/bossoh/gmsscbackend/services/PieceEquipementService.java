package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.Dto.PieceEquipementDto;

import java.util.List;

public interface PieceEquipementService {

    List<PieceEquipementDto> listOfPieceEquipement();

    PieceEquipementDto savePieceEquipement(PieceEquipementDto pedto);

    PieceEquipementDto getPieceEquipementyId(Long id);

    boolean deletePieceEquipement(Long id);
}
