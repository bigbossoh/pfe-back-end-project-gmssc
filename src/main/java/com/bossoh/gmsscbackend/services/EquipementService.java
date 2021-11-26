package com.bossoh.gmsscbackend.services;
import com.bossoh.gmsscbackend.Dto.EquipementDto;

import java.util.List;

public interface EquipementService {

    List<EquipementDto> listOfEquipement();

    EquipementDto saveEquipement(EquipementDto equipementDto);

    EquipementDto getEquipementById(Long id);

    EquipementDto getEquipementByCode(String codeEquipement);

    boolean deleteEquipement(Long id);


}
