package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.Dto.GroupeIntervenantDto;
import com.bossoh.gmsscbackend.services.impl.GroupeIntervenantServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/groupe-intervenant")
@RequiredArgsConstructor
public class GroupeIntervenantController {

    private final GroupeIntervenantServiceImpl groupeIntervenantService;

    @GetMapping(value=APP_ROOT+"/groupe-intervenant/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupeIntervenantDto> getGroupeIntervenant(){
        return groupeIntervenantService.listOfGroupeIntervenant();
    }

    @GetMapping(value=APP_ROOT+"/groupe-intervenant/{IdgrpInterv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupeIntervenantDto getSignalerPanneDto(@PathVariable("IdgrpInterv") Long IdgrpInterv) {
        return groupeIntervenantService.getGroupeIntervenantById(IdgrpInterv);
    }

    @PostMapping(value =APP_ROOT+"/groupe-intervenant/savegrpeIntervenant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupeIntervenantDto saveGroupeIntervenant(@RequestBody GroupeIntervenantDto groupeIntervenantDto) {

        return groupeIntervenantService.saveGroupeIntervenant(groupeIntervenantDto);
    }

    @DeleteMapping(value = APP_ROOT+"/groupe-intervenant/delete/{IdgrpInterv}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteGroupeIntervenantById(@PathVariable("IdgrpInterv") Long IdgrpInterv) {
        return groupeIntervenantService.deleteGroupeIntervenant(IdgrpInterv);
    }
}
