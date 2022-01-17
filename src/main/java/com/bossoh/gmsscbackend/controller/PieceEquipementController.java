package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.Dto.PieceEquipementDto;
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
    public List<PieceEquipementDto> getAllPieceEquipement(){
        return pieceEquipementService.listOfPieceEquipement();
    }


    @GetMapping(value=APP_ROOT+"/PieceEquipements/{IdPieceEqpt}",produces = MediaType.APPLICATION_JSON_VALUE)
    public PieceEquipementDto getPieceEquipementByID(@PathVariable("IdPieceEqpt") Long IdPieceEqpt) {
        return pieceEquipementService.getPieceEquipementyId(IdPieceEqpt);
    }

    @PostMapping(value =APP_ROOT+"/PieceEquipements/savePieceEquipement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PieceEquipementDto savePieceEquipement(@RequestBody  PieceEquipementDto pieceEquipement) {

        return pieceEquipementService.savePieceEquipement(pieceEquipement);
    }

    @DeleteMapping(value = APP_ROOT+"/PieceEquipements/delete/{IdPieceEqpt}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deletePieceEquipementById(@PathVariable("IdPieceEqpt") Long IdPieceEqpt) {
        return pieceEquipementService.deletePieceEquipement(IdPieceEqpt);
    }

}
