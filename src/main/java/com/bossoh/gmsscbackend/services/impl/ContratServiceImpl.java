package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Validator.BienImmobilierValidator;
import com.bossoh.gmsscbackend.Validator.ContratValidator;
import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.Contrat;
import com.bossoh.gmsscbackend.entities.Equipement;
import com.bossoh.gmsscbackend.entities.Societe;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityExeception;
import com.bossoh.gmsscbackend.repositories.ContratRepository;
import com.bossoh.gmsscbackend.repositories.EquipementRepository;
import com.bossoh.gmsscbackend.repositories.SocieteRepository;
import com.bossoh.gmsscbackend.services.ContratService;
import com.bossoh.gmsscbackend.utils.UtilRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ContratServiceImpl implements ContratService {
    private  static ContratRepository contratRepository;
    private final SocieteRepository societeRepository;
    private final EquipementRepository equipementRepository;
    private final UtilRandom utilRandom;
    @Override
    public List<Contrat> listOfContrat() {
        log.info("We are going to get back all contrats");
        return contratRepository.findAll();
    }

    @Override
    public Contrat saveContrat(Contrat contrat) {
        log.info("We are going to save a new contrat {}",contrat);
        List<String> errors= ContratValidator.Validate(contrat);
        if(!errors.isEmpty()){
            throw new InvalidEntityExeception("L'objet contrat possede certains de ses attributs null",
                    ErrorCodes.CONTRAT_NOT_VALID,errors);
        }
        Optional<Societe> soc=societeRepository.findById(contrat.getSociete().getId());
        if(soc.isPresent()){
            contrat.setSociete(soc.get());
        }else
        {
            throw new InvalidEntityExeception("L'objet societe possede certains de ses attributs null",
                    ErrorCodes.SOCIETE_NOT_FOUND);
        }
        contrat.setCodeContrat(utilRandom.generatedRandomString(6));
        log.info("contrat is saved...");

        Contrat c= contratRepository.save(contrat);
        if (contrat.getListEquipement() != null) {
            contrat.getListEquipement().forEach(Eqpe -> {
                Eqpe.setContrat(c);
                Equipement savedEqpe = equipementRepository.save(Eqpe);
            });
        }

        return c;

    }

    @Override
    public Contrat updateContrat(Contrat contrat) {
        log.info("We are going to update a existing contrat");
        Optional<Contrat> con= contratRepository.
                findById(contrat.getId());
        if(con.isPresent()){
            log.info("The contrat is well existing...");
            List<String> errors=ContratValidator.Validate(contrat);
            if(!errors.isEmpty()){
                throw new InvalidEntityExeception("L'objet contrat possede certains de ses attributs null",
                        ErrorCodes.CONTRAT_NOT_VALID,errors);
            }
            log.info("Contrat updated...");
            return contratRepository.save(contrat);

        }else {
            throw new InvalidEntityExeception("L'objet contrat doesn't exist in the BD",
                    ErrorCodes.CONTRAT_NOT_FOUND);
        }
    }

    @Override
    public Contrat getContratId(Long id) {
        log.info("We are going to get back a Contrat by ID {}",id);

        if (id == null) {
            log.error("Contrat ID is null");
            return null;
        }
        return contratRepository.findContratById(id).orElseThrow(
                ()-> new EntityNotFoundException("Aucun bien avec l'ID = " + id + " "
                        + "n' ete trouve dans la BDD",  ErrorCodes.CONTRAT_NOT_FOUND)
        );
    }

    @Override
    public Contrat getContratByCode(String codeContrat) {
        log.info("We are going to get back a Contrat by ID {}",codeContrat);

        if (codeContrat == null) {
            log.error("Contrat ID is null");
            return null;
        }
        return contratRepository.findContratByCodeContrat(codeContrat).orElseThrow(
                ()-> new EntityNotFoundException("Aucune bien immobilier avec l'ID = " + codeContrat + " "
                        + "n' ete trouve dans la BDD",  ErrorCodes.CONTRAT_NOT_FOUND)
        );
    }

    @Override
    public boolean deleteEquipement(Long id) {
        log.info("Nous supprimons un bien si l'ID de la contract existe ");
        boolean exist=contratRepository.existsById(id);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun bien avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.CONTRAT_NOT_FOUND);

        }
        contratRepository.deleteById(id);
        return true;
    }

//    public Equipement updateEquipement(Contrat contrat){
//
//    }
}
