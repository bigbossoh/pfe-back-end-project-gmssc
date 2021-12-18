package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.Dto.PreventiveDto;
import com.bossoh.gmsscbackend.services.impl.PreventiveServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/intervention/preventive")
@RequiredArgsConstructor
public class PreventiveController {

    private  final PreventiveServiceImpl preventiveService;

    @GetMapping(value=APP_ROOT+"/intervention/preventive/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PreventiveDto> getAllPreventive(){
        return preventiveService.listOfPreventives();
    }

    @GetMapping(value=APP_ROOT+"/intervention/preventive/{IdPreventive}",produces = MediaType.APPLICATION_JSON_VALUE)
    public PreventiveDto getPreventiveByID(@PathVariable("IdPreventive") Long IdPreventive) {
        return preventiveService.getPreventiveById(IdPreventive);
    }

    @PostMapping(value =APP_ROOT+"/intervention/preventive/savepreventive",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PreventiveDto savePreventive(@RequestBody PreventiveDto preventiveDto) {

        return preventiveService.savePreventive(preventiveDto);
    }

    @DeleteMapping(value = APP_ROOT+"/intervention/preventive/delete/{IdPreventive}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deletePreventiveById(@PathVariable("IdPreventive") Long IdPreventive) {
        return preventiveService.deletePreventive(IdPreventive);
    }
}
