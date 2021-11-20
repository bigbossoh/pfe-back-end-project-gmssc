package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.Equipement;

import java.util.List;

public interface EquipementService {

    List<Equipement> listOfEquipement();

    Equipement saveEquipement(Equipement equipement);

    Equipement updateEquipement(Equipement equipement);

    Equipement getEquipementById(Long id);

    Equipement getEquipementByCode(String codeEquipement);

    boolean deleteEquipement(Long id);


}
