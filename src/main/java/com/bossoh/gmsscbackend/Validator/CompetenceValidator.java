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
            errors.addAll(IntervenantValidator.validate(null));
            return errors;
        }
        if(!StringUtils.hasLength(dto.getJob())){
            errors.add("Veuillez renseillez le nom de la competence");
        }

        if(dto.getIntervenantDto()==null || dto.getIntervenantDto().getId()==null){
            errors.add("Veuillez selectionner un utilisateur");
        }
        errors.addAll(IntervenantValidator.validate(dto.getIntervenantDto()));
        return errors;
    }
}
