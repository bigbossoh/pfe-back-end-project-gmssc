package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.Dto.CompetenceDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CompetenceValidator {

    public static List<String> validate(CompetenceDto dto){
        List<String>errors= new ArrayList<>();
        if(dto==null ){
            errors.add("Veuillez renseillez le nom de la competence");
            errors.addAll(UtilisateurValidator.validate(null));
            return errors;
        }
        if(!StringUtils.hasLength(dto.getJob())){
            errors.add("Veuillez renseillez le nom de la competence");
        }

        if(dto.getUserDto()==null || dto.getUserDto().getId()==null){
            errors.add("Veuillez selectionner un utilisateur");
        }
        errors.addAll(UtilisateurValidator.validate(dto.getUserDto()));
        return errors;
    }
}
