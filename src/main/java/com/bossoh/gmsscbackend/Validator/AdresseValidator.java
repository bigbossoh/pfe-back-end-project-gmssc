package com.bossoh.gmsscbackend.Validator;


import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;

import com.bossoh.gmsscbackend.entities.Adresse;

public class AdresseValidator {

  public static List<String> validate(Adresse adresse) {
    List<String> errors = new ArrayList<>();

    if (adresse == null) {
      errors.add("Veuillez renseigner l'adresse 1'");
      errors.add("Veuillez renseigner la ville'");
      errors.add("Veuillez renseigner le pays'");
      errors.add("Veuillez renseigner le code postal'");
      return errors;
    }
    if (!StringUtils.hasLength(adresse.getAdresse1())) {
      errors.add("Veuillez renseigner l'adresse 1'");
    }
    if (!StringUtils.hasLength(adresse.getVille())) {
      errors.add("Veuillez renseigner la ville'");
    }
    if (!StringUtils.hasLength(adresse.getPays())) {
      errors.add("Veuillez renseigner le pays'");
    }
    if (!StringUtils.hasLength(adresse.getAdresse1())) {
      errors.add("Veuillez renseigner le code postal'");
    }
    return errors;
  }

}
