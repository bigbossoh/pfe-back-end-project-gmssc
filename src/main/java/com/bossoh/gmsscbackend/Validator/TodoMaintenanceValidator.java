package com.bossoh.gmsscbackend.Validator;

import com.bossoh.gmsscbackend.Dto.SocieteDto;
import com.bossoh.gmsscbackend.Dto.TodoMaintenanceDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TodoMaintenanceValidator {
    public static List<String> validate(TodoMaintenanceDto dto ){
        List<String> errors= new ArrayList<String>();
        if (dto == null) {
            errors.add("Veuillez renseigner la description de l'action");
            return errors;
        }
        if ( !StringUtils.hasLength(dto.getDescription())) {
            errors.add("Veuillez renseigner la description de l'action");
        }
        return errors;
    }
}
