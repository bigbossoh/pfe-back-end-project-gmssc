package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.Dto.ContratDto;
import com.bossoh.gmsscbackend.services.impl.ContratServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/contrats")
@RequiredArgsConstructor
public class ContratController {
    private final ContratServiceImpl contratService;
    @GetMapping(value=APP_ROOT+"/contrats/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ContratDto> getAllContrat(){
        return contratService.listOfContrat();
    }

    @GetMapping(value=APP_ROOT+"/contrats/{IdContrat}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ContratDto getContratByID(@PathVariable("IdContrat") Long IdContrat) {
        return contratService.getContratId(IdContrat);
    }

    @PostMapping(value =APP_ROOT+"/contrats/savecontrat",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ContratDto saveContrat(@RequestBody ContratDto contrat) {

        return contratService.saveContrat(contrat);
    }

    @DeleteMapping(value = APP_ROOT+"/contrats/delete/{Idcontrats}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteBienById(@PathVariable("Idcontrats") Long Idcontrats) {
        return contratService.deleteContrat(Idcontrats);
    }

}
