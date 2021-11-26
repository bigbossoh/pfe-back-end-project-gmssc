package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.Dto.ContratDto;

import java.util.ArrayList;
import java.util.List;

public class ContratValidator {
    public static List<String> Validate(ContratDto contrat){
        List<String> errors = new ArrayList<>();
        if (contrat==null){
            errors.add("Veuillez renseigner la date de signature");
            errors.add("Veuillez renseigner la societe de maintenance");
            return errors;
        }
        if (contrat.getDateSignature()==null) {
            errors.add("Veuillez renseigner la date de signature");
        }
        if(contrat.getSocieteDto()==null){
            errors.add("Veuillez renseigner la societe de maintenance");
        }
        return errors;
    }
}
