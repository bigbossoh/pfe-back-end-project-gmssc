package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.Dto.ChangerMotDePasseUtilisateurDto;
import com.bossoh.gmsscbackend.Dto.UtilisateurDto;
import com.bossoh.gmsscbackend.services.UtilisateurService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@RequiredArgsConstructor
@Api(APP_ROOT+"/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @GetMapping(value=APP_ROOT+"/utilisateurs/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UtilisateurDto> getListeDesUtilisateurs() {
        return utilisateurService.listOfUtilisateur();
    }
    @GetMapping(value=APP_ROOT+"/utilisateurs/{IdUser}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurDto getUtilisateurByID(@PathVariable("IdUser") Long IdUser) {
        return utilisateurService.findById(IdUser);
    }
    @GetMapping(value=APP_ROOT+"/utilisateur/{email}",produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurDto getUtilisateurByEmail(@PathVariable("email") String email) {
        return utilisateurService.findByEmail(email);
    }
    @PostMapping(value =APP_ROOT+"/utilisateurs/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurDto save(@RequestBody UtilisateurDto dto) {
        return utilisateurService.save(dto);
    }
    @PostMapping(value =APP_ROOT+"/utilisateurs/update/password",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurDto changerMotDePasse(@RequestBody ChangerMotDePasseUtilisateurDto dto) {
        return utilisateurService.changerMotDePasse(dto);
    }
    @DeleteMapping(value = APP_ROOT+"/utilisateurs/delete/{IdUser}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUtilisateur(@PathVariable("IdUser") Long IdUser) {
       utilisateurService.delete(IdUser);
    }
}
