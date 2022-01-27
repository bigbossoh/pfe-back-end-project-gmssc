package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.Dto.IntervenantDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class IntervenantValidator {

    public static List<String> validate(IntervenantDto intervenantDto) {
        List<String> errors = new ArrayList<>();

        if (intervenantDto == null) {
            errors.add("Veuillez renseigner le nom de l'intervenant");
            errors.add("Veuillez renseigner le numero mobile de l'intervenant");
            errors.add("Veuillez selectionner une société");
            errors.add("Veuillez renseigner l'email de l'intervenant");
            return errors;
        }


        if (!StringUtils.hasLength(intervenantDto.getNomInterv())) {
            errors.add("Veuillez renseigner le nom de l'intervenant");
        }
        if (!StringUtils.hasLength(intervenantDto.getMobile())) {
            errors.add("Veuillez renseigner le numero mobile de l'intervenant");
        }
        if (!StringUtils.hasLength(intervenantDto.getEmailIntervenant())) {
            errors.add("Veuillez renseigner l'email de l'intervenant");
        }
        if (intervenantDto.getSocieteDto() == null || intervenantDto.getSocieteDto().getId() == null) {
            errors.add("Veuillez selectionner une société");
        }
        return errors;
    }
}
