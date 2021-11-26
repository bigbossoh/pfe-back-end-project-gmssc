package com.bossoh.gmsscbackend.Validator;

import java.util.ArrayList;
import java.util.List;

import com.bossoh.gmsscbackend.Dto.SocieteDto;
import org.springframework.util.StringUtils;

import com.bossoh.gmsscbackend.entities.Societe;

public class SocieteValidator {
	public static List<String> validate(SocieteDto socdto){
		List<String> errors= new ArrayList<String>();
        if (socdto == null) {
			errors.add("Veuillez renseigner la denomination de la societe");
			errors.add("Veuillez renseigner la description de l'activité");
			errors.add("Veuillez renseigner le mobile de la société");
			errors.addAll(AdresseValidator.validate(null));
            return errors;
        }

		if ( !StringUtils.hasLength(socdto.getDenomination())) {
			errors.add("Veuillez renseigner la denomination de la societe");
		}
		if ( !StringUtils.hasLength(socdto.getDescriptionActivite())) {
			errors.add("Veuillez renseigner la description de l'activité");
		}
		if ( !StringUtils.hasLength(socdto.getMobile())) {
			errors.add("Veuillez renseigner le mobile de la société");
		}
		errors.addAll(AdresseValidator.validate(socdto.getAdresse()));
		return errors;
	}

}
