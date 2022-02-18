package com.bossoh.gmsscbackend.Validator;
import com.bossoh.gmsscbackend.Dto.IntervenantGroupeIntervenantDto;

import java.util.ArrayList;
import java.util.List;

public class IntervenantGroupeIntervenantValidator {
    public static List<String> validator(IntervenantGroupeIntervenantDto userGrpeInterDto){
        List<String>errors= new ArrayList<>();
        if(userGrpeInterDto==null ){
            errors.add("Veuillez renseigner l'intervenant");
            errors.add("Veuillez renseillez le gorupe d'intervenant");
            return errors;
        }
        if(userGrpeInterDto.getIntervenantDto()==null){
            errors.add("Veuillez renseigner l'intervenant");
        }
        if( userGrpeInterDto.getGroupeIntervenantDto()==null){
            errors.add("Veuillez renseillez le gorupe d'intervenant");
        }
        return errors;
    }
}
