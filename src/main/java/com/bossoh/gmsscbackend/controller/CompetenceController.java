package com.bossoh.gmsscbackend.controller;
import com.bossoh.gmsscbackend.Dto.CompetenceDto;
import com.bossoh.gmsscbackend.services.impl.CompetenceServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;
@RestController
@Api(APP_ROOT+"/Competence")
@RequiredArgsConstructor
public class CompetenceController {

    private  final CompetenceServiceImpl competenceService;

    @GetMapping(value=APP_ROOT+"/competences/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CompetenceDto> getAllCompetence(){
        return competenceService.listOfCompetenceDto();
    }

    @GetMapping(value=APP_ROOT+"/competences/{IdCompetences}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CompetenceDto getCompetenceByID(@PathVariable("IdCompetences") Long IdCompetences) {
        return competenceService.getCompetenceById(IdCompetences);
    }

    @PostMapping(value =APP_ROOT+"/competences/savecompetences",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CompetenceDto saveCompetence(@RequestBody CompetenceDto competenceDto) {

        return competenceService.saveCompetence(competenceDto);
    }

    @DeleteMapping(value = APP_ROOT+"/competences/delete/{IdCompetences}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteCompetencesById(@PathVariable("IdCompetences") Long IdCompetences) {
        return competenceService.deleteCompetence(IdCompetences);
    }
}
