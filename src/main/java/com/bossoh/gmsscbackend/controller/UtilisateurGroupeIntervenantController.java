package com.bossoh.gmsscbackend.controller;
import com.bossoh.gmsscbackend.Dto.UtilisateurGroupeIntervenantDto;
import com.bossoh.gmsscbackend.services.impl.UtilisateurGroupeIntervenantServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/utilisateurGroupeIntervenant")
@RequiredArgsConstructor
public class UtilisateurGroupeIntervenantController {

    private final UtilisateurGroupeIntervenantServiceImpl utilisateurGroupeIntervenantService;

    @GetMapping(value=APP_ROOT+"/utilisateurGroupeIntervenant/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UtilisateurGroupeIntervenantDto> getAllUtilisateurGroupeIntervenant(){
        return utilisateurGroupeIntervenantService.listOfUtilisateurGroupeIntervenant();
    }

    @GetMapping(value=APP_ROOT+"/utilisateurGroupeIntervenant/{IduserGrpeInterv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurGroupeIntervenantDto getUtilisateurGroupeIntervenantByID(@PathVariable("IduserGrpeInterv") Long IduserGrpeInterv) {
        return utilisateurGroupeIntervenantService.getUtilisateurGroupeIntervenantById(IduserGrpeInterv);
    }

    @PostMapping(value =APP_ROOT+"/utilisateurGroupeIntervenant/saveusergroupeintervenant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurGroupeIntervenantDto saveUtilisateurGroupeIntervenant(@RequestBody UtilisateurGroupeIntervenantDto userGroupeIntervenantDto) {

        return utilisateurGroupeIntervenantService.saveUtilisateurGroupeIntervenant(userGroupeIntervenantDto);
    }

    @DeleteMapping(value = APP_ROOT+"/UtilisateurGroupeIntervenant/delete/{IduserGrpeInterv}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteUtilisateurGroupeIntervenantById(@PathVariable("IduserGrpeInterv") Long IduserGrpeInterv) {
        return utilisateurGroupeIntervenantService.deleteUtilisateurGroupeIntervenant(IduserGrpeInterv);
    }

}
