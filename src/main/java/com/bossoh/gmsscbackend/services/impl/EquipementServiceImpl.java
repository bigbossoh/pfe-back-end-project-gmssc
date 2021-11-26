package com.bossoh.gmsscbackend.services.impl;
import com.bossoh.gmsscbackend.Dto.EquipementDto;
import com.bossoh.gmsscbackend.Validator.EquipementValidor;
import com.bossoh.gmsscbackend.entities.*;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityExeception;
import com.bossoh.gmsscbackend.exceptions.InvalidOperationException;
import com.bossoh.gmsscbackend.repositories.PieceEquipementRepository;
import com.bossoh.gmsscbackend.utils.UtilRandom;
import com.bossoh.gmsscbackend.repositories.EquipementRepository;
import com.bossoh.gmsscbackend.repositories.PieceRepository;
import com.bossoh.gmsscbackend.services.EquipementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

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
    public EquipementDto saveEquipement(EquipementDto equipementDto) {
        log.info("We are going to save a new equipement {}",equipementDto);
        List<String> errors= EquipementValidor.validate(equipementDto);
        if(!errors.isEmpty()){
            log.error("l'equipement n'est pas valide {}",errors);
            throw new InvalidEntityExeception("Certain attributs de l'object equipement sont null.",
                    ErrorCodes.EQUIPEMENT_NOT_VALID,errors);
        }

        if(equipementDto.getId() ==null){
            equipementDto.setCodeEquipement(utilRandom.generatedRandomString(6));
        }
        return EquipementDto.fromEntity(equipementRepository.save(EquipementDto.toEntity(equipementDto)));
    }

    @Override
    public EquipementDto getEquipementById(Long id) {
        log.info("We are going to get back the equipement en fonction de l'ID {} du bien", id);
        if(id==null){
            log.error("you are provided a null ID for the equipement");
            return null;
        }
        return equipementRepository.findEquipementById(id)
                .map(EquipementDto::fromEntity)
                .orElseThrow(()->new InvalidEntityExeception("Aucun equipement has been found with ID "+id,
                        ErrorCodes.EQUIPEMENT_PIECE_NOT_FOUND));
    }

    @Override
    public EquipementDto getEquipementByCode(String codeEquipement) {
        log.info("We are going to get back the equipement with code {}",codeEquipement);
        if (!StringUtils.hasLength(codeEquipement)){
            log.error("you are not provided a code for this equipement {}", codeEquipement);
            return  null;
        }
        return equipementRepository.findEquipementByCodeEquipement(codeEquipement)
                .map(EquipementDto::fromEntity)
                .orElseThrow(()->new InvalidEntityExeception("Aucun Equipement has been found with Code "+codeEquipement,
                        ErrorCodes.EQUIPEMENT_NOT_FOUND));
    }

    @Override
    public boolean deleteEquipement(Long id) {
        log.info("We are going to delete a Equipement {}", id);
        if (id==null){
            log.error("you are provided a null ID for the bien immobilier");
            return false;
        }
        boolean exist=equipementRepository.existsById(id);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun bien avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.EQUIPEMENT_NOT_FOUND);

        }

        equipementRepository.deleteById(id);
        return true;
    }
    @Override
    public List<EquipementDto> listOfEquipement() {
        log.info("We are going to take back all the bien immobilier");

        return equipementRepository.findAll().stream()
                .map(EquipementDto::fromEntity)
                .collect(Collectors.toList());
    }
}
