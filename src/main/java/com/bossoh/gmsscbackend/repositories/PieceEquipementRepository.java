package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.Equipement;
import com.bossoh.gmsscbackend.entities.PieceEquipement;
import com.bossoh.gmsscbackend.entities.Pieces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PieceEquipementRepository extends JpaRepository<PieceEquipement,Long> {
    Optional<PieceEquipement> findById(Long id);
    List<PieceEquipement> findPieceEquipementByPieces(Pieces pieces);
    List<PieceEquipement> findPieceEquipementByEquipement(Equipement eqt);
}
