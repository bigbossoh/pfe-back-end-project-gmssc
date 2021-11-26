package com.bossoh.gmsscbackend.services;
import com.bossoh.gmsscbackend.Dto.PiecesDto;
import com.bossoh.gmsscbackend.entities.Pieces;

import java.util.List;

public interface PieceService {


    List<PiecesDto> listOfPieces();

    PiecesDto savePiece(PiecesDto pieceDto);

    PiecesDto getPieceById(Long idDto);

    PiecesDto getPieceByCode(String codePieceDto);

    boolean deletePiece(Long idDto);


}
