package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.entities.Contrat;

import java.util.ArrayList;
import java.util.List;

public class ContratValidator {
    public static List<String> Validate(Contrat contrat){
        List<String> errors = new ArrayList<>();
        if (contrat==null){
            errors.add("Veuillez renseigner les equipements");
            errors.add("Veuillez renseigner la date de signature");
            errors.add("Veuillez renseigner la societe de maintenance");
            return errors;
        }
        if (contrat.getDateSignature()==null)
        {
            errors.add("Veuillez renseigner la date de signature");
        }
        if(contrat.getSociete()==null){
            errors.add("Veuillez renseigner la societe de maintenance");
        }
        if(contrat.getListEquipement()==null){
            errors.add("Veuillez renseigner les equipements");
        }
        return errors;
    }
}
