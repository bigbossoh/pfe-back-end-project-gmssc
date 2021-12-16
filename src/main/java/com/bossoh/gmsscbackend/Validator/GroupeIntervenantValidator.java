package com.bossoh.gmsscbackend.Validator;
import com.bossoh.gmsscbackend.Dto.GroupeIntervenantDto;

import java.util.ArrayList;
import java.util.List;

public class GroupeIntervenantValidator {
    public static List<String> validate(GroupeIntervenantDto dto){
        List<String>errors= new ArrayList<>();
        if(dto==null ){
            errors.add("Veuillez selectionner une signalisation de panne");
            return errors;
        }

        if(dto.getSignalerPanneDto()==null || dto.getSignalerPanneDto().getId()==null){
            errors.add("Veuillez selectionner une signalisation de panne");
        }
        return errors;
    }
}
