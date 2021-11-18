package com.bossoh.gmsscbackend.Validator;
import com.bossoh.gmsscbackend.entities.Pieces;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PieceValidator {
    public static List<String> validate(Pieces pieces){
        List<String>errors= new ArrayList<>();
        if(pieces ==null ){
            errors.add("Veuillez renseillez le nom de la pièce.");
            errors.add("Veuillez renseigner le type de pièce.");
            errors.add("Veuillez renseigner le nom du batiments.");
            errors.add("Veuillez selectionner un bien immobilier.");
            return errors;
        }
        if(!StringUtils.hasLength(pieces.getNomPiece())){
            errors.add("Veuillez renseillez le nom de la pièce.");
        }
        if( !StringUtils.hasLength(pieces.getTypeSalle().toString())){
            errors.add("Veuillez renseigner le type de pièce.");
        }
        if (pieces.getNomBatiment() == null){
            errors.add("Veuillez renseigner le nom du batiments.");
        }
        if(pieces.getBienImmobilier()==null || pieces.getBienImmobilier().getId()==null){
            errors.add("Veuillez selectionner un bien immobilier.");
        }
        return errors;
    }
}
