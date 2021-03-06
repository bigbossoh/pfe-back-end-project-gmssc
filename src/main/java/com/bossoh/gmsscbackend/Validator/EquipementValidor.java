package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.Dto.EquipementDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EquipementValidor {
    public static List<String> validate(EquipementDto dto){
        List<String>errors= new ArrayList<>();
        if(dto==null ){
            errors.add("Veuillez renseillez la marque de l'equipement");
            errors.add("Veuillez renseigner le type du climatisateur");
            errors.add("Veuillez renseigner le nom de la société a quile l'équipement appartient");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getMarque())){
            errors.add("Veuillez renseillez la marque de l'equipement");
        }
        if( !StringUtils.hasLength(dto.getTypeClim())){
            errors.add("Veuillez renseigner le type du climatisateur");
        }
        if (dto.getSocieteDto() == null || dto.getSocieteDto().getId() == null){
            errors.add("Veuillez renseigner le nom de la société a quile l'équipement appartient");
        }
        return errors;
    }
}
