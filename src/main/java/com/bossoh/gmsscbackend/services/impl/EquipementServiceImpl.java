package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Validator.EquipementValidor;
import com.bossoh.gmsscbackend.defaultData.UtilRandom;
import com.bossoh.gmsscbackend.entities.Equipement;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityExeception;
import com.bossoh.gmsscbackend.repositories.EquipementRepository;
import com.bossoh.gmsscbackend.repositories.PieceEquipementRepository;
import com.bossoh.gmsscbackend.repositories.PieceRepository;
import com.bossoh.gmsscbackend.services.EquipementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EquipementServiceImpl implements EquipementService {
    private final EquipementRepository equipementRepository;
    private final PieceRepository pieceRepository;
    private final PieceEquipementRepository pieceEquipementRepository;
    private final UtilRandom utilRandom;
    @Override
    public List<Equipement> listOfEquipement() {
        log.info("We are going to get back all equipement de climatisation");
        return equipementRepository.findAll();
    }

    @Override
    public Equipement saveEquipement(Equipement equipement) {
        log.info("We are saving a new equipement {}",equipement);
        List<String> errors= EquipementValidor.validate(equipement);
        log.error(String.valueOf(errors));
        if(!errors.isEmpty()){
            throw new InvalidEntityExeception("L'objet equipement possede certains de ses attributs null",
                    ErrorCodes.EQUIPEMENT_NOT_VALID,errors);
        }

        equipement.setCodeEquipement(utilRandom.generatedRandomString(6));
        return equipementRepository.save(equipement);
    }

    @Override
    public Equipement updateEquipement(Equipement equipement) {
        log.info("We are going to update a existing equipement");
        Optional<Equipement> equipementClimatique= equipementRepository.
                findById(equipement.getId());
        if(equipementClimatique.isPresent()){
            log.info("The equipement is well existing...");
            List<String> errors=EquipementValidor.validate(equipement);
            if(!errors.isEmpty()){
                throw new InvalidEntityExeception("L'objet bien immobilier possede certains de ses attributs null",
                        ErrorCodes.EQUIPEMENT_NOT_VALID,errors);
            }
            return equipementRepository.save(equipement);
        }else {
            throw new InvalidEntityExeception("L'objet bien immobilier doesn't exist in the BD",
                    ErrorCodes.EQUIPEMENT_NOT_FOUND);
        }
    }

    @Override
    public Equipement getEquipementyId(Long id) {

        log.info("We are going to get back a equipement by ID {}",id);

        if (id == null) {
            log.error("equipement ID is null");
            return null;
        }
        return equipementRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Aucun equipement avec l'ID = " + id + " "
                        + "n' ete trouve dans la BDD",  ErrorCodes.EQUIPEMENT_NOT_FOUND)
        );
    }

    @Override
    public Equipement getEquipementByCode(String codeEquipement) {
        log.info("We are going to get back a Equipement by ID {}",codeEquipement);

        if (codeEquipement == null) {
            log.error("bien immobilier ID is null");
            return null;
        }
        return equipementRepository.findEquipementByCodeEquipement(codeEquipement).orElseThrow(
                ()-> new EntityNotFoundException("Aucune bien immobilier avec l'ID = " + codeEquipement + " "
                        + "n' ete trouve dans la BDD",  ErrorCodes.BIEN_IMMOBILIER_NOT_FOUND)
        );
    }

    @Override
    public boolean deleteEquipement(Long id) {
        log.info("Nous supprimons un bien si l'ID de la equipement existe ");
        boolean exist=equipementRepository.existsById(id);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun bien avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.SOCIETE_NOT_FOUND);
        }
        equipementRepository.deleteById(id);
        return true;
    }
}
