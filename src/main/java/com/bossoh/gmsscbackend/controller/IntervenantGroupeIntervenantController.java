package com.bossoh.gmsscbackend.controller;
import com.bossoh.gmsscbackend.Dto.IntervenantGroupeIntervenantDto;
import com.bossoh.gmsscbackend.services.impl.IntervenantGroupeIntervenantServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/utilisateurGroupeIntervenant")
@RequiredArgsConstructor
public class IntervenantGroupeIntervenantController {

    private final IntervenantGroupeIntervenantServiceImpl utilisateurGroupeIntervenantService;

    @GetMapping(value=APP_ROOT+"/utilisateurGroupeIntervenant/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IntervenantGroupeIntervenantDto> getAllUtilisateurGroupeIntervenant(){
        return utilisateurGroupeIntervenantService.listOfUtilisateurGroupeIntervenant();
    }
    @GetMapping(value=APP_ROOT+"/utilisateurGroupeIntervenant/alldistinct",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Object[]> getAllDistinctGroupeIntervenant(){
        return utilisateurGroupeIntervenantService.listOfDistinctGroupeIntervenant();
    }
    @GetMapping(value=APP_ROOT+"/utilisateurGroupeIntervenant/allparGroupeIntervenant/{IduserGrpeInterv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IntervenantGroupeIntervenantDto> getlistOfUtilisateurGroupeIntervenantByGroupeIntervenant(@PathVariable("IduserGrpeInterv") Long IduserGrpeInterv){
        return utilisateurGroupeIntervenantService.listOfUtilisateurGroupeIntervenantByGroupeIntervenant(IduserGrpeInterv);
    }

    @GetMapping(value=APP_ROOT+"/utilisateurGroupeIntervenant/{IduserGrpeInterv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public IntervenantGroupeIntervenantDto getUtilisateurGroupeIntervenantByID(@PathVariable("IduserGrpeInterv") Long IduserGrpeInterv) {
        return utilisateurGroupeIntervenantService.getUtilisateurGroupeIntervenantById(IduserGrpeInterv);
    }

    @PostMapping(value =APP_ROOT+"/utilisateurGroupeIntervenant/saveusergroupeintervenant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public IntervenantGroupeIntervenantDto saveUtilisateurGroupeIntervenant(@RequestBody IntervenantGroupeIntervenantDto userGroupeIntervenantDto) {

        return utilisateurGroupeIntervenantService.saveUtilisateurGroupeIntervenant(userGroupeIntervenantDto);
    }

    @DeleteMapping(value = APP_ROOT+"/UtilisateurGroupeIntervenant/delete/{IduserGrpeInterv}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteUtilisateurGroupeIntervenantById(@PathVariable("IduserGrpeInterv") Long IduserGrpeInterv) {
        return utilisateurGroupeIntervenantService.deleteUtilisateurGroupeIntervenant(IduserGrpeInterv);
    }

}
