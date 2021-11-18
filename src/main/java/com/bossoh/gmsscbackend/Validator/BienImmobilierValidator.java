package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BienImmobilierValidator {
    public static List<String> validate(BienImmobilier bienImmobilier){
        List<String>errors= new ArrayList<>();
        if(bienImmobilier==null ){
            errors.add("Veuillez renseillez le nom du bien immobilier");
            errors.add("Veuillez renseigner le type de bien immobilier");
            errors.add("Veuillez renseigner le nombre de batiments qu'il ya sur ce bien immobilier");
            errors.add("Veuillez selectionner une société");
            return errors;
        }
        if(!StringUtils.hasLength(bienImmobilier.getNomBienImmobilier())){
            errors.add("Veuillez renseillez le nom du bien immobilier");
        }
        if( !StringUtils.hasLength(bienImmobilier.getTypeBienImmobilier().toString())){
            errors.add("Veuillez renseigner le type de bien immobilier");
        }
        if (bienImmobilier.getNbreBatiments() == null){
            errors.add("Veuillez renseigner le nombre de batiments qu'il ya sur ce bien immobilier");
        }
        if(bienImmobilier.getSociete()==null || bienImmobilier.getSociete().getId()==null){
            errors.add("Veuillez selectionner une société");
        }
        return errors;
    }
}
