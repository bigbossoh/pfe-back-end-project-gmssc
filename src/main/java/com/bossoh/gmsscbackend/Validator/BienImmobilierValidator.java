package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.Dto.BienImmobilierDto;
import com.bossoh.gmsscbackend.entities.BienImmobilier;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class BienImmobilierValidator {
    public static List<String> validate(BienImmobilierDto dto){
        List<String>errors= new ArrayList<>();
        if(dto==null ){
            errors.add("Veuillez renseillez le nom du bien immobilier");
            errors.add("Veuillez renseigner le type de bien immobilier");
            errors.add("Veuillez renseigner le nombre de batiments qu'il ya sur le site dudit bien immobilier");
            errors.add("Veuillez selectionner une société");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getNomBienImmobilier())){
            errors.add("Veuillez renseillez le nom du bien immobilier");
        }
        if( !StringUtils.hasLength(dto.getTypeBienImmobilier())){
            errors.add("Veuillez renseigner le type de bien immobilier");
        }
        if (dto.getNbreBatiments() == null){
            errors.add("Veuillez renseigner le nombre de batiments qu'il ya sur le site dudit bien immobilier");
        }
        if(dto.getSocieteDto()==null || dto.getSocieteDto().getId()==null){
            errors.add("Veuillez selectionner une société");
        }
        return errors;
    }
}
