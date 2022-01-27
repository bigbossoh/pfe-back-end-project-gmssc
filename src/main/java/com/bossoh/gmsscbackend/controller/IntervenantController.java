package com.bossoh.gmsscbackend.controller;
import com.bossoh.gmsscbackend.Dto.IntervenantDto;
import com.bossoh.gmsscbackend.services.impl.IntervenantServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/intervenants")
@RequiredArgsConstructor
public class IntervenantController {

    private  final IntervenantServiceImpl intervenantService;

    @GetMapping(value=APP_ROOT+"/intervenants/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IntervenantDto> getAllIntervenants(){
        return intervenantService.listOfIntervenants();
    }

    @GetMapping(value=APP_ROOT+"/intervenants/allbysociete/{IdSociete}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IntervenantDto> listOfIntervenantsBySocieteId(@PathVariable("IdSociete") Long IdSociete){
        return intervenantService.listOfIntervenantsBySocieteId(IdSociete);
    }
    @GetMapping(value=APP_ROOT+"/intervenants/allbysocietebyfunction/{fonctionInterv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IntervenantDto> listOfIntervenantsByfunction(@PathVariable("fonctionInterv") String fonctionInterv){
        return intervenantService.listOfIntervenantsByFonctionInterv(fonctionInterv);
    }
    @GetMapping(value=APP_ROOT+"/intervenants/allbysocieteandfunction/{IdSociete}/{fonctionInterv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<IntervenantDto> listOfIntervenantsBySocieteIdAndFunction(@PathVariable("IdSociete") Long IdSociete,@PathVariable("fonctionInterv") String fonctionInterv){
        return intervenantService.listOfIntervenantsBySocieteIDAndFunction(IdSociete,fonctionInterv);
    }
    @GetMapping(value=APP_ROOT+"/intervenants/{IdInterv}",produces = MediaType.APPLICATION_JSON_VALUE)
    public IntervenantDto getIntervenantByID(@PathVariable("IdInterv") Long IdInterv) {
        return intervenantService.getIntervenantById(IdInterv);
    }

    @PostMapping(value =APP_ROOT+"/intervenants/saveIntervenant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public IntervenantDto saveIntervenant(@RequestBody IntervenantDto intervDto) {

        return intervenantService.saveIntervenant(intervDto);
    }

    @DeleteMapping(value = APP_ROOT+"/intervenants/delete/{IdInterv}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteIntervenantById(@PathVariable("IdInterv") Long IdInterv) {
        return intervenantService.deleteIntervenant(IdInterv);
    }
}
