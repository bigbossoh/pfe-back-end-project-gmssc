package com.bossoh.gmsscbackend.services.impl;
import com.bossoh.gmsscbackend.Dto.GroupeIntervenantDto;
import com.bossoh.gmsscbackend.Validator.GroupeIntervenantValidator;
import com.bossoh.gmsscbackend.entities.*;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.repositories.GroupeIntervenantRepository;
import com.bossoh.gmsscbackend.repositories.SignalePanneRepository;
import com.bossoh.gmsscbackend.services.GroupeIntervenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class GroupeIntervenantServiceImpl implements GroupeIntervenantService {

    private final GroupeIntervenantRepository groupeIntervenantRepository ;
    private final SignalePanneRepository signalePanneRepository ;

    @Override
    public GroupeIntervenantDto saveGroupeIntervenant(GroupeIntervenantDto groupeIntervenantDto) {
        log.info("We are going to save a new GroupeIntervenant {}",groupeIntervenantDto);
        List<String> errors= GroupeIntervenantValidator.validate(groupeIntervenantDto);
        if(!errors.isEmpty()){
            log.error("Le groupe intervenant n'est pas valide {}",errors);
            throw new InvalidEntityException("Certain attributs de l'object groupe interbenant sont null.",
                    ErrorCodes.GROUPEINTERVENANT_NOT_VALID,errors);
        }
        Optional<SignalerPanne> signalerPanne = signalePanneRepository.findById(groupeIntervenantDto.getSignalerPanneDto().getId());
        if (!signalerPanne.isPresent()) {
            log.warn("Le signalement de panne with ID {} was not found in the DB", groupeIntervenantDto.getSignalerPanneDto().getId());
            throw new EntityNotFoundException("Aucun signalement de panne avec l'ID" +groupeIntervenantDto.getSignalerPanneDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.SOCIETE_NOT_FOUND);
        }

        groupeIntervenantDto.setDateAffectation(LocalDate.now());
        GroupeIntervenant saveGroupeIntervenant=groupeIntervenantRepository.save(GroupeIntervenantDto.toEntity(groupeIntervenantDto));
        return GroupeIntervenantDto.fromEntity(saveGroupeIntervenant);
    }

    @Override
    public GroupeIntervenantDto getGroupeIntervenantById(Long idDto) {
        log.info("We are going to get back the Groupe Intervenant en fonction de l'ID {} du GroupeIntervenant", idDto);
        if(idDto==null){
            log.error("you are provided a null ID for the GroupeIntervenant");
            return null;
        }
        return groupeIntervenantRepository.findById(idDto)
                .map(GroupeIntervenantDto::fromEntity)
                .orElseThrow(()->new InvalidEntityException("Aucun GroupeIntervenant has been found with ID "+idDto,
                        ErrorCodes.GROUPEINTERVENANT_NOT_FOUND));
    }

    @Override
    public boolean deleteGroupeIntervenant(Long idDto) {
        log.info("We are going to delete a Groupe Intervenant {}", idDto);
        if (idDto==null){
            log.error("you are provided a null ID for the Groupe Intervenant");
            return false;
        }
        boolean exist=groupeIntervenantRepository.existsById(idDto);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun bien avec l'ID = " + idDto + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.GROUPEINTERVENANT_NOT_FOUND);

        }

        groupeIntervenantRepository.deleteById(idDto);
        return true;
    }

    @Override
    public List<GroupeIntervenantDto> listOfGroupeIntervenant() {
        log.info("We are going to take back all the GroupeIntervenant");

        return groupeIntervenantRepository.findAll().stream()
                .map(GroupeIntervenantDto::fromEntity)
                .collect(Collectors.toList());
    }
}
