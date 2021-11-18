package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.PieceEquipement;
import com.bossoh.gmsscbackend.services.impl.PieceEquipementServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/PieceEquipement")
@RequiredArgsConstructor
public class PieceEquipementController {

    private final PieceEquipementServiceImpl pieceEquipementService;
    @GetMapping("/all")
    public List<PieceEquipement> getAllPieceEquipement(){
        return pieceEquipementService.listOfPieceEquipement();
    }

    @GetMapping(path = "{IdPieceEqpt}")
    public PieceEquipement getPieceEquipementByID(@PathVariable("IdPieceEqpt") Long IdPieceEqpt) {
        return pieceEquipementService.getPieceEquipementyId(IdPieceEqpt);
    }
    @PostMapping(path = "/savePieceEquipement")
    public PieceEquipement savePieceEquipement(@RequestBody  PieceEquipement pieceEquipement) {

        return pieceEquipementService.savePieceEquipement(pieceEquipement);
    }
    @DeleteMapping(path = "/delete/{IdBien}")
    public boolean deletePieceEquipementById(@PathVariable("IdPieceEqpt") Long IdPieceEqpt) {
        return pieceEquipementService.deletePieceEquipement(IdPieceEqpt);
    }
    @PutMapping(path="/update")
    public PieceEquipement updatingPieceEquipement(@RequestBody PieceEquipement pieceEqpt){
        return pieceEquipementService.updatePieceEquipement(pieceEqpt);
    }
}
