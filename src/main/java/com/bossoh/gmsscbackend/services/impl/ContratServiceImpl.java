package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Dto.ContratDto;
import com.bossoh.gmsscbackend.Dto.EquipementDto;
import com.bossoh.gmsscbackend.Validator.ContratValidator;
import com.bossoh.gmsscbackend.entities.*;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityExeception;
import com.bossoh.gmsscbackend.exceptions.InvalidOperationException;
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
    private final ContratRepository contratRepository;
    private final SocieteRepository societeRepository;
    private final EquipementRepository equipementRepository;
    private final UtilRandom utilRandom;


    @Override
    public ContratDto saveContrat(ContratDto contrat) {
        log.info("We are going to save a new contrat {}", contrat);
        List<String> errors = ContratValidator.Validate(contrat);
        if (!errors.isEmpty()) {
            log.error("le contrat n'est pas valide {}", errors);
            throw new InvalidEntityExeception("Certain attributs de l'object contrat sont null.",
                    ErrorCodes.CONTRAT_NOT_VALID, errors);
        }
        Optional<Societe> contratsoc = societeRepository.findById(contrat.getSocieteDto().getId());
        if (!contratsoc.isPresent()) {
            log.warn("La societe with ID {} was not found in the DB", contrat.getSocieteDto().getId());
            throw new EntityNotFoundException("Aucune société  avec l'ID" + contrat.getSocieteDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.SOCIETE_NOT_FOUND);
        }
        if (contrat.getId() == null) {
            contrat.setCodeContrat(utilRandom.generatedRandomString(6));
        }
        Contrat saveContrat = contratRepository.save(ContratDto.toEntity(contrat));

        List<String> equipementErrors = new ArrayList<>();

        if (contrat.getEquipementDtos() != null) {
            contrat.getEquipementDtos().forEach(eqpt -> {
                if (eqpt == null) {

                    equipementErrors.add("On a des equipements qui sont null");
                } else {
                    Optional<Equipement> equipement = equipementRepository.findById(eqpt.getId());
                    if (!equipement.isPresent()) {
                        equipementErrors.add("L'article avec l'ID " + eqpt.getId() + " n'existe pas");
                    }
                }
            });
        }

        if (!equipementErrors.isEmpty()) {
            log.warn("");
            throw new InvalidEntityExeception("Equipement n'existe pas dans la BDD", ErrorCodes.EQUIPEMENT_NOT_FOUND, equipementErrors);
        }
        if (contrat.getEquipementDtos() != null) {
            contrat.getEquipementDtos().forEach(ligEqpt -> {
                Optional<Equipement> Equip=equipementRepository.findById(ligEqpt.getId());
                if(Equip.isPresent()){
                    Equipement equiptCharge=Equip.get();
                    equiptCharge.setContratEquipement(saveContrat);
                    Equipement savedEqpt = equipementRepository.save(equiptCharge);
                }else
                {
                    throw new InvalidEntityExeception("L'objet equipement possede certains de ses attributs null",
                            ErrorCodes.SOCIETE_NOT_FOUND);
                }
            });
        }
        return ContratDto.fromEntity(saveContrat);

    }

    @Override
    public ContratDto getContratId(Long id) {
        log.info("We are going to get back the contrat en fonction de l'ID {} du bien", id);
        if (id == null) {
            log.error("you are provided a null ID for the contrat");
            return null;
        }
        return contratRepository.findContratById(id)
                .map(ContratDto::fromEntity)
                .orElseThrow(() -> new InvalidEntityExeception("Aucun bien immobilier has been found with ID " + id,
                        ErrorCodes.CONTRAT_NOT_FOUND));
    }

    @Override
    public boolean deleteContrat(Long id) {
        log.info("We are going to delete a contrat {}", id);
        if (id == null) {
            log.error("you are provided a null ID for the contrat");
            return false;
        }
        boolean exist = contratRepository.existsById(id);
        if (!exist) {
            throw new EntityNotFoundException("Aucun bien avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD", ErrorCodes.CONTRAT_NOT_FOUND);
        }
        Optional<Contrat> c = contratRepository.findContratById(id);
        if (!c.isPresent()) {
            log.warn("La societe with ID {} was not found in the DB", c);
            throw new EntityNotFoundException("Aucune société  avec l'ID" + c + " n'a ete trouve dans la BDD",
                    ErrorCodes.SOCIETE_NOT_FOUND);
        }
        List<Equipement> eqpList = equipementRepository.findEquipementByContratEquipement(c);
        if (!eqpList.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un contrat déjà utilisé",
                    ErrorCodes.CONTRAT_ALREADY_IN_USE);
        }
        contratRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ContratDto> listOfContrat() {
        return null;
    }
}