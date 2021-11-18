package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.PieceEquipement;
import com.bossoh.gmsscbackend.services.impl.PieceEquipementServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/PieceEquipements")
@RequiredArgsConstructor
public class PieceEquipementController {

    private final PieceEquipementServiceImpl pieceEquipementService;

    @GetMapping(value=APP_ROOT+"/PieceEquipements/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PieceEquipement> getAllPieceEquipement(){
        return pieceEquipementService.listOfPieceEquipement();
    }


    @GetMapping(value=APP_ROOT+"/PieceEquipements/{IdPieceEqpt}",produces = MediaType.APPLICATION_JSON_VALUE)
    public PieceEquipement getPieceEquipementByID(@PathVariable("IdPieceEqpt") Long IdPieceEqpt) {
        return pieceEquipementService.getPieceEquipementyId(IdPieceEqpt);
    }

    @PostMapping(value =APP_ROOT+"/PieceEquipements/savePieceEquipement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PieceEquipement savePieceEquipement(@RequestBody  PieceEquipement pieceEquipement) {

        return pieceEquipementService.savePieceEquipement(pieceEquipement);
    }

    @DeleteMapping(value = APP_ROOT+"/PieceEquipements/delete/{IdBien}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deletePieceEquipementById(@PathVariable("IdPieceEqpt") Long IdPieceEqpt) {
        return pieceEquipementService.deletePieceEquipement(IdPieceEqpt);
    }

    @PutMapping(value =APP_ROOT+"/PieceEquipements/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PieceEquipement updatingPieceEquipement(@RequestBody PieceEquipement pieceEqpt){
        return pieceEquipementService.updatePieceEquipement(pieceEqpt);
    }
}
