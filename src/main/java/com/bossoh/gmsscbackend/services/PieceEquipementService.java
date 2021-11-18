package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.entities.Equipement;
import com.bossoh.gmsscbackend.entities.PieceEquipement;
import com.bossoh.gmsscbackend.entities.Pieces;

import java.util.List;

public interface PieceEquipementService {
    List<PieceEquipement> listOfPieceEquipement();

    PieceEquipement savePieceEquipement(PieceEquipement pieceEquipement);

    PieceEquipement updatePieceEquipement(PieceEquipement pieceEquipement);

    PieceEquipement getPieceEquipementyId(Long id);

    boolean deletePieceEquipement(Long id);
}
