package com.bossoh.gmsscbackend.services;
import com.bossoh.gmsscbackend.entities.Pieces;

import java.util.List;

public interface PieceService {


    List<Pieces> listOfPieces();

    Pieces savePiece(Pieces pieces);

    Pieces updatePiece(Pieces pieces);

    Pieces getPieceById(Long id);

    Pieces getPieceByCode(String codePiece);

    boolean deletePiece(Long id);


}
