package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Validator.BienImmobilierValidator;
import com.bossoh.gmsscbackend.utils.UtilRandom;
import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.Societe;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityExeception;
import com.bossoh.gmsscbackend.repositories.BienImmobilierRepository;
import com.bossoh.gmsscbackend.repositories.SocieteRepository;
import com.bossoh.gmsscbackend.services.BienImmobilierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BienImmobilierImpl implements BienImmobilierService {
    private final BienImmobilierRepository bienImmobilierRepository;
    private final SocieteRepository societeRepository;
    private final UtilRandom utilRandom;

    @Override
    public BienImmobilier saveBienImmobilier(BienImmobilier bien) {
        log.info("We are going to save a new bien immobilier {}",bien);
        List<String> errors= BienImmobilierValidator.validate(bien);
        if(!errors.isEmpty()){
             throw new InvalidEntityExeception("L'objet bien immobilier possede certains de ses attributs null",
                     ErrorCodes.BIEN_IMMOBILIER_NOT_VALID,errors);
        }
        Optional<Societe> soc=societeRepository.findById(bien.getSociete().getId());
        if(soc.isPresent()){
            bien.setSociete(soc.get());
        }else
        {
            throw new InvalidEntityExeception("L'objet societe possed certains de ses attributs null",
                    ErrorCodes.SOCIETE_NOT_FOUND);
        }
        bien.setCodeBienImmobilier(utilRandom.generatedRandomString(6));
        log.info("bien immobilier is saved...");
        return bienImmobilierRepository.save(bien);

    }

    @Override
    public BienImmobilier updateBienImmobilier(BienImmobilier bien) {
        log.info("We are going to update a existing bien immobilier");
        Optional<BienImmobilier> bienImmo= bienImmobilierRepository.
                findBienImmobilierById(bien.getId());
        if(bienImmo.isPresent()){
            log.info("The bien immobilier is well existing...");
            List<String> errors=BienImmobilierValidator.validate(bien);
            if(!errors.isEmpty()){
                throw new InvalidEntityExeception("L'objet bien immobilier possede certains de ses attributs null",
                        ErrorCodes.BIEN_IMMOBILIER_NOT_VALID,errors);
            }
            log.info("bien immobilier updated...");
            return bienImmobilierRepository.save(bien);
        }else {
            throw new InvalidEntityExeception("L'objet bien immobilier doesn't exist in the BD",
                    ErrorCodes.BIEN_IMMOBILIER_NOT_FOUND);
        }
    }

    @Override
    public BienImmobilier getBienImmobilierById(Long id) {
        log.info("We are going to get back a bien immobilier by ID {}",id);

        if (id == null) {
            log.error("bien immobilier ID is null");
            return null;
        }
        return bienImmobilierRepository.findBienImmobilierById(id).orElseThrow(
                ()-> new EntityNotFoundException("Aucun bien avec l'ID = " + id + " "
                        + "n' ete trouve dans la BDD",  ErrorCodes.BIEN_IMMOBILIER_NOT_FOUND)
        );
    }

    @Override
    public BienImmobilier getBienImmobilierByCode(String codeBien) {
        log.info("We are going to get back a bien immobilier by ID {}",codeBien);

        if (codeBien == null) {
            log.error("bien immobilier ID is null");
            return null;
        }
        return bienImmobilierRepository.findBienImmobilierByCodeBienImmobilier(codeBien).orElseThrow(
                ()-> new EntityNotFoundException("Aucune bien immobilier avec l'ID = " + codeBien + " "
                        + "n' ete trouve dans la BDD",  ErrorCodes.BIEN_IMMOBILIER_NOT_FOUND)
        );
    }

    @Override
    public boolean deleteBienImmobilier(Long id) {
        log.info("Nous supprimons un bien si l'ID de la bien immobilier existe ");
        boolean exist=bienImmobilierRepository.existsById(id);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun bien avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.SOCIETE_NOT_FOUND);

        }
        bienImmobilierRepository.deleteById(id);
        return true;
    }

    @Override
    public List<BienImmobilier> listOfBienImmobiliers() {
        log.info("We are going to get back all bien immobilier by ID");
        return bienImmobilierRepository.findAll();
    }
}
