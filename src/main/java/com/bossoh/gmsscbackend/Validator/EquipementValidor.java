package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.entities.Equipement;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EquipementValidor {
    public static List<String> validate(Equipement equipement){
        List<String>errors= new ArrayList<>();
        if(equipement==null ){
            errors.add("Veuillez renseillez la marque de l'equipement");
            errors.add("Veuillez renseigner le type du climatisateur");
            errors.add("Veuillez renseigner la puissance du climatiseur");
            return errors;
        }
        if(!StringUtils.hasLength(equipement.getMarque())){
            errors.add("Veuillez renseillez la marque de l'equipement");
        }
        if( !StringUtils.hasLength(equipement.getTypeClim())){
            errors.add("Veuillez renseigner le type du climatisateur");
        }
        if (equipement.getPuissance() == null){
            errors.add("Veuillez renseigner la puissance du climatiseur");
        }

        return errors;
    }
}
