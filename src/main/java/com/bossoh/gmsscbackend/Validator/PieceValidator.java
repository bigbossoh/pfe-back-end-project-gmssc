package com.bossoh.gmsscbackend.Validator;
import com.bossoh.gmsscbackend.Dto.PiecesDto;
import com.bossoh.gmsscbackend.entities.Pieces;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PieceValidator {
    public static List<String> validate(PiecesDto dto){
        List<String>errors= new ArrayList<>();
        if(dto ==null ){
            errors.add("Veuillez renseillez le nom de la pièce.");
            errors.add("Veuillez renseigner le type de pièce.");
            errors.add("Veuillez renseigner un bien immobilier.");
            return errors;
        }
        if(!StringUtils.hasLength(dto.getNomPiece())){
            errors.add("Veuillez renseillez le nom de la pièce.");
        }
        if( !StringUtils.hasLength(dto.getTypeSalle())){
            errors.add("Veuillez renseigner le type de pièce.");
        }

        if(dto.getBienImmobilierDto()==null || dto.getBienImmobilierDto().getId()==null){
            errors.add("Veuillez renseigner un bien immobilier.");
        }
        return errors;
    }
}
