package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.Dto.CorrectiveDto;
import com.bossoh.gmsscbackend.services.impl.CorrectiveServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;
@RestController
@Api(APP_ROOT+"/intervention/corrective")
@RequiredArgsConstructor
public class CorrectiveController {

    private  final CorrectiveServiceImpl correctiveService;

    @GetMapping(value=APP_ROOT+"/intervention/corrective/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CorrectiveDto> getAllCorrective(){
        return correctiveService.listOfCorrectives();
    }

    @GetMapping(value=APP_ROOT+"/intervention/corrective/{IdCorrective}",produces = MediaType.APPLICATION_JSON_VALUE)
    public CorrectiveDto getCorrectiveByID(@PathVariable("IdCorrective") Long IdCorrective) {
        return correctiveService.getCorrectiveById(IdCorrective);
    }

    @PostMapping(value =APP_ROOT+"/intervention/corrective/savecorrective",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CorrectiveDto saveCorrective(@RequestBody CorrectiveDto correctiveDto) {

        return correctiveService.saveCorrective(correctiveDto);
    }

    @DeleteMapping(value = APP_ROOT+"/intervention/corrective/delete/{IdCorrective}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteBienById(@PathVariable("IdCorrective") Long IdCorrective) {
        return correctiveService.deleteCorrective(IdCorrective);
    }

}