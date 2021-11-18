package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.services.impl.BienImmobilierImpl;
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

    private  final BienImmobilierImpl bienImmobilierImpl;

    @GetMapping(value=APP_ROOT+"/biens/all",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BienImmobilier> getAllBien(){
        return bienImmobilierImpl.listOfBienImmobiliers();
    }

    @GetMapping(value=APP_ROOT+"/biens/{IdBien}",produces = MediaType.APPLICATION_JSON_VALUE)
    public BienImmobilier getBienByID(@PathVariable("IdBien") Long IdBien) {
        return bienImmobilierImpl.getBienImmobilierById(IdBien);
    }

    @GetMapping(value=APP_ROOT+"/biens/getbien/{codebien}",produces = MediaType.APPLICATION_JSON_VALUE)
    public BienImmobilier getBienByID(@PathVariable("codebien") String codebien) {
        return bienImmobilierImpl.getBienImmobilierByCode(codebien);
    }
    @PostMapping(value =APP_ROOT+"/biens/savebien",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BienImmobilier saveBienImmobilier(@RequestBody  BienImmobilier bien) {

        return bienImmobilierImpl.saveBienImmobilier(bien);
    }

    @DeleteMapping(value = APP_ROOT+"/biens/delete/{IdBien}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteBienById(@PathVariable("IdBien") Long IdBien) {
        return bienImmobilierImpl.deleteBienImmobilier(IdBien);
    }

    @PutMapping(value =APP_ROOT+"/biens/update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BienImmobilier updatingBienImmobilier(@RequestBody BienImmobilier bien){
        return bienImmobilierImpl.updateBienImmobilier(bien);
    }
}
