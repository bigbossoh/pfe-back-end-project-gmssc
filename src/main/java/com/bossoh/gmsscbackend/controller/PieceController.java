package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.Dto.PiecesDto;
import com.bossoh.gmsscbackend.services.impl.PieceServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/pieces")
@RequiredArgsConstructor
public class PieceController {

    private final PieceServiceImpl pieceService;


    @GetMapping(value=APP_ROOT+"/pieces/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PiecesDto> getListeDesPieces() {
        return pieceService.listOfPieces();
    }

    @GetMapping(value=APP_ROOT+"/pieces/{IdPiece}",produces = MediaType.APPLICATION_JSON_VALUE)
    public PiecesDto getPieceByID(@PathVariable("IdPiece") Long IdPiece) {
        return pieceService.getPieceById(IdPiece);
    }
    @GetMapping(value=APP_ROOT+"/pieces/getPiece/{codePiece}",produces = MediaType.APPLICATION_JSON_VALUE)
    public PiecesDto getPieceByCodePiece(@PathVariable("CodePiece") String CodePiece) {
        return pieceService.getPieceByCode(CodePiece);
    }
    @PostMapping(value =APP_ROOT+"/pieces/savepiece",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PiecesDto savePiece(@RequestBody PiecesDto pieces) {

        return pieceService.savePiece(pieces);
    }
    @DeleteMapping(value = APP_ROOT+"/pieces/delete/{idPiece}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deletePieceById(@PathVariable("idPiece") Long idPiece) {
        return pieceService.deletePiece(idPiece);
    }

}

