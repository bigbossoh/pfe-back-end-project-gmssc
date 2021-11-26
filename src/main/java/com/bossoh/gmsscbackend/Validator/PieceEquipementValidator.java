package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.Dto.PieceEquipementDto;

import java.util.ArrayList;
import java.util.List;

public class PieceEquipementValidator {
    public static List<String> validator(PieceEquipementDto pieceEquipementDto){
        List<String>errors= new ArrayList<>();
        if(pieceEquipementDto==null ){
            errors.add("Veuillez renseigner la pièce");
            errors.add("Veuillez renseillez l'equipement");
            return errors;
        }
        if(pieceEquipementDto.getEquipementDto()==null){
            errors.add("Veuillez renseillez l'equipement");
        }
        if( pieceEquipementDto.getPiecesDto()==null){
            errors.add("Veuillez renseigner la pièce");
        }
        return errors;
    }
}
