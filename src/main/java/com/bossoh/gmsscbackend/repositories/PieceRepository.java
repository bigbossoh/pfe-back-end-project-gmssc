package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.Pieces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PieceRepository extends JpaRepository<Pieces,Long> {
    Optional<Pieces> findById(Long Id);
    Optional<Pieces> findPieceByCodePiece(String codePiece);
    List<Pieces> findAllByBienImmobilierId(Long id);
}
