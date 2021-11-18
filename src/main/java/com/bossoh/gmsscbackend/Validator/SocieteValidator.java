package com.bossoh.gmsscbackend.Validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.bossoh.gmsscbackend.entities.Societe;

public class SocieteValidator {
	public static List<String> validate(Societe soc){
		List<String> errors= new ArrayList<String>();
		
		if (soc==null || !StringUtils.hasLength(soc.getDenomination())) {
			errors.add("Veuillez renseigner le nom du responsable");
		}
		if (soc==null || !StringUtils.hasLength(soc.getDescriptionActivite())) {
			errors.add("Veuillez renseigner la description de l'activité");
		}
		if (soc==null || !StringUtils.hasLength(soc.getMobile())) {
			errors.add("Veuillez renseigner le mobile de la société");
		}
		return errors;
	}

}
