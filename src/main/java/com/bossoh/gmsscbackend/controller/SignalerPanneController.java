package com.bossoh.gmsscbackend.controller;
import com.bossoh.gmsscbackend.Dto.SignalerPanneDto;
import com.bossoh.gmsscbackend.services.impl.SignalerPanneServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/signaler-panne")
@RequiredArgsConstructor
public class SignalerPanneController {
    private final SignalerPanneServiceImpl signalerPanneService;

    @GetMapping(value=APP_ROOT+"/signaler-panne/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SignalerPanneDto> getAllContrat(){
        return signalerPanneService.listOfSignalerPanne();
    }

    @GetMapping(value=APP_ROOT+"/signaler-panne/{IdPanne}",produces = MediaType.APPLICATION_JSON_VALUE)
    public SignalerPanneDto getSignalerPanneDto(@PathVariable("IdPanne") Long IdPanne) {
        return signalerPanneService.getSignalerPanneId(IdPanne);
    }

    @PostMapping(value =APP_ROOT+"/signaler-panne/savecontrat",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public SignalerPanneDto saveSignalerPanne(@RequestBody SignalerPanneDto signalerPanneDto) {

        return signalerPanneService.saveSignalerPanne(signalerPanneDto);
    }

    @DeleteMapping(value = APP_ROOT+"/signaler-panne/delete/{IdPanne}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteBienById(@PathVariable("IdPanne") Long IdPanne) {
        return signalerPanneService.deleteSignalerPanne(IdPanne);
    }
}
