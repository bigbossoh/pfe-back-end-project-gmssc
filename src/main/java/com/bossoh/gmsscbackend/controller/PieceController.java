package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.entities.Pieces;
import com.bossoh.gmsscbackend.services.impl.PieceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/piece")
@RequiredArgsConstructor
public class PieceController {

    private final PieceServiceImpl pieceService;

    @GetMapping("/all")
    public List<Pieces> getListeDesPieces() {
        return pieceService.listOfPieces();
    }
    @GetMapping(path = "{IdPiece}")
    public Pieces getPieceByID(@PathVariable("IdPiece") Long IdPiece) {
        return pieceService.getPieceById(IdPiece);
    }
    @GetMapping(path = "/getPiece/{CodePiece}")
    public Pieces getPieceByID(@PathVariable("CodePiece") String CodePiece) {
        return pieceService.getPieceByCode(CodePiece);
    }
    @PostMapping(path = "/savepiece")
    public Pieces savePiece(@RequestBody Pieces pieces) {

        return pieceService.savePiece(pieces);
    }
    @DeleteMapping(path = "/delete/{idPiece}")
    public boolean deletePieceById(@PathVariable("idPiece") Long idPiece) {
        return pieceService.deletePiece(idPiece);
    }
    @PutMapping(path="/update")
    public Pieces updatingSociete(@RequestBody Pieces pieces) {
        return pieceService.updatePiece(pieces);
    }
}

