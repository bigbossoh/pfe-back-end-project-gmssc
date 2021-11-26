package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.Dto.EquipementDto;
import com.bossoh.gmsscbackend.services.impl.EquipementServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/equipements")
@RequiredArgsConstructor
public class EquipementController {
    private final EquipementServiceImpl equipementService;


    @GetMapping(value=APP_ROOT+"/equipements/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EquipementDto> getListeDesEquipements() {
        return equipementService.listOfEquipement();
    }
    @GetMapping(value=APP_ROOT+"/equipements/{IdEquipement}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipementDto getEquipementByID(@PathVariable("IdEquipement") Long IdEquipement) {
        return equipementService.getEquipementById(IdEquipement);
    }
    @GetMapping(value=APP_ROOT+"/equipements/getPiece/{CodeEquipement}",produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipementDto getPieceByID(@PathVariable("CodeEquipement") String CodeEquipement) {
        return equipementService.getEquipementByCode(CodeEquipement);
    }
    @PostMapping(value =APP_ROOT+"/equipements/saveEquipement",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public EquipementDto savePiece(@RequestBody EquipementDto equipement) {

        return equipementService.saveEquipement(equipement);
    }

    @DeleteMapping(value = APP_ROOT+"/equipements/delete/{idEquipement}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteEquipementById(@PathVariable("idEquipement") Long idEquipement) {
        return equipementService.deleteEquipement(idEquipement);
    }
}
