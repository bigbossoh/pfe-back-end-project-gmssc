package com.bossoh.gmsscbackend.Validator;
import com.bossoh.gmsscbackend.Dto.UtilisateurGroupeIntervenantDto;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurGroupeIntervenantValidator {
    public static List<String> validator(UtilisateurGroupeIntervenantDto userGrpeInterDto){
        List<String>errors= new ArrayList<>();
        if(userGrpeInterDto==null ){
            errors.add("Veuillez renseigner la utilisateur");
            errors.add("Veuillez renseillez le gorupe d'intervenant");
            return errors;
        }
        if(userGrpeInterDto.getUtilisateurDto()==null){
            errors.add("Veuillez renseigner la utilisateur");
        }
        if( userGrpeInterDto.getGroupeIntervenantDto()==null){
            errors.add("Veuillez renseillez le gorupe d'intervenant");
        }
        return errors;
    }
}
