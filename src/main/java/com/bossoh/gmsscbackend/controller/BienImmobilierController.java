package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.Dto.BienImmobilierDto;
import com.bossoh.gmsscbackend.services.impl.BienImmobilierServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@Api(APP_ROOT+"/biens")
@RequiredArgsConstructor
public class BienImmobilierController {

    private  final BienImmobilierServiceImpl bienImmobilierImpl;

    @GetMapping(value=APP_ROOT+"/biens/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BienImmobilierDto> getAllBien(){
        return bienImmobilierImpl.listOfBienImmobiliers();
    }

    @GetMapping(value=APP_ROOT+"/biens/{IdBien}",produces = MediaType.APPLICATION_JSON_VALUE)
    public BienImmobilierDto getBienByID(@PathVariable("IdBien") Long IdBien) {
        return bienImmobilierImpl.getBienImmobilierById(IdBien);
    }

    @GetMapping(value=APP_ROOT+"/biens/getbien/{codebien}",produces = MediaType.APPLICATION_JSON_VALUE)
    public BienImmobilierDto getBienByCode(@PathVariable("codebien") String codebien) {
        return bienImmobilierImpl.getBienImmobilierByCode(codebien);
    }
    @PostMapping(value =APP_ROOT+"/biens/savebien",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BienImmobilierDto saveBienImmobilier(@RequestBody  BienImmobilierDto bienDto) {

        return bienImmobilierImpl.saveBienImmobilier(bienDto);
    }

    @DeleteMapping(value = APP_ROOT+"/biens/delete/{IdBien}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteBienById(@PathVariable("IdBien") Long IdBien) {
        return bienImmobilierImpl.deleteBienImmobilier(IdBien);
    }

}
