package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.entities.PieceEquipement;


import java.util.ArrayList;
import java.util.List;

public class PieceEquipementValidator {
    public static List<String> validator(PieceEquipement pieceEquipement){
        List<String>errors= new ArrayList<>();
        if(pieceEquipement==null ){
            errors.add("Veuillez renseigner la pièce");
            errors.add("Veuillez renseillez l'equipement");
            return errors;
        }
        if(pieceEquipement.getEquipement()==null){
            errors.add("Veuillez renseillez l'equipement");
        }
        if( pieceEquipement.getPieces()==null){
            errors.add("Veuillez renseigner la pièce");
        }
        return errors;
    }
}
