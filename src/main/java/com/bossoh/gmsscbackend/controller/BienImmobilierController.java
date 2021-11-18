package com.bossoh.gmsscbackend.controller;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.services.impl.BienImmobilierImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bien")
@RequiredArgsConstructor
public class BienImmobilierController {

    private  final BienImmobilierImpl bienImmobilierImpl;
    @GetMapping("/all")
    public List<BienImmobilier> getAllBien(){
        return bienImmobilierImpl.listOfBienImmobiliers();
    }

    @GetMapping(path = "{IdBien}")
    public BienImmobilier getBienByID(@PathVariable("IdBien") Long IdBien) {
        return bienImmobilierImpl.getBienImmobilierById(IdBien);
    }
    @GetMapping(path = "/getbien/{codebien}")
    public BienImmobilier getBienByID(@PathVariable("codebien") String codebien) {
        return bienImmobilierImpl.getBienImmobilierByCode(codebien);
    }
    @PostMapping(path = "/savebien")
    public BienImmobilier saveBienImmobilier(@RequestBody  BienImmobilier bien) {

        return bienImmobilierImpl.saveBienImmobilier(bien);
    }
    @DeleteMapping(path = "/delete/{IdBien}")
    public boolean deleteBienById(@PathVariable("IdBien") Long IdBien) {
        return bienImmobilierImpl.deleteBienImmobilier(IdBien);
    }
    @PutMapping(path="/update")
    public BienImmobilier updatingBienImmobilier(@RequestBody BienImmobilier bien){
        return bienImmobilierImpl.updateBienImmobilier(bien);
    }
}
