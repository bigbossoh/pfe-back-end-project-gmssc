package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.Dto.SignalerPanneDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SignalerPanneValidator {
    public static List<String> Validate(SignalerPanneDto dto){
        List<String>errors= new ArrayList<>();
        if(dto==null ){

            errors.add("Veuillez renseigner l'objet de la signalisation");
            errors.add("Veuillez renseigner la description de la panne");
            errors.add("Veuillez selectionner un utilisateur");
            errors.add("Veuillez selectionner une piece");
            return errors;
        }

        if( !StringUtils.hasLength(dto.getObjetPanne())){
            errors.add("Veuillez renseigner l'objet de la signalisation");
        }
        if (!StringUtils.hasLength(dto.getDescriptionPanne())){
            errors.add("Veuillez renseigner la description de la panne");
        }
        if(dto.getUtilisateurDto()==null || dto.getUtilisateurDto().getId()==null){
            errors.add("Veuillez selectionner un utilisateur");
        }
        if(dto.getPiecesDto()==null || dto.getPiecesDto().getId()==null){
            errors.add("Veuillez selectionner une piece");
        }
        return errors;
    }
}
