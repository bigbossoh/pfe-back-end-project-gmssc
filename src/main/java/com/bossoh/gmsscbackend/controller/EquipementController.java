package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.entities.Equipement;
import com.bossoh.gmsscbackend.services.impl.EquipementServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/equipement")
@RequiredArgsConstructor
public class EquipementController {
    private final EquipementServiceImpl equipementService;

    @GetMapping("/all")
    public List<Equipement> getListeDesEquipements() {
        return equipementService.listOfEquipement();
    }
    @GetMapping(path = "{IdEquipement}")
    public Equipement getEquipementByID(@PathVariable("IdEquipement") Long IdEquipement) {
        return equipementService.getEquipementyId(IdEquipement);
    }
    @GetMapping(path = "/getPiece/{CodeEquipement}")
    public Equipement getPieceByID(@PathVariable("CodeEquipement") String CodeEquipement) {
        return equipementService.getEquipementByCode(CodeEquipement);
    }
    @PostMapping(path = "/saveEquipement")
    public Equipement savePiece(@RequestBody Equipement equipement) {

        return equipementService.saveEquipement(equipement);
    }
    @DeleteMapping(path = "/delete/{idEquipement}")
    public boolean deleteEquipementById(@PathVariable("idEquipement") Long idEquipement) {
        return equipementService.deleteEquipement(idEquipement);
    }
    @PutMapping(path="/update")
    public Equipement updatingSociete(@RequestBody Equipement equipement) {
        return equipementService.updateEquipement(equipement);
    }
}
