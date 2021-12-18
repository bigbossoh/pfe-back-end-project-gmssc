package com.bossoh.gmsscbackend.services.impl;
import com.bossoh.gmsscbackend.Dto.PreventiveDto;
import com.bossoh.gmsscbackend.entities.*;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.repositories.GroupeIntervenantRepository;
import com.bossoh.gmsscbackend.repositories.InterventionRepository;
import com.bossoh.gmsscbackend.repositories.PreventiveRepository;
import com.bossoh.gmsscbackend.services.PreventiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PreventiveServiceImpl implements PreventiveService {

    private final InterventionRepository interventionRepository;
    private final GroupeIntervenantRepository groupeIntervenantRepository;
    private  final PreventiveRepository preventiveRepository;
    @Override
    public PreventiveDto savePreventive(PreventiveDto preventiveDto) {
        log.info("We are going to save a new intervention Preventive {}",preventiveDto);
        Optional<GroupeIntervenant> grpIntervenant = groupeIntervenantRepository.findById(preventiveDto.getGroupeIntervenantDto().getId());
        if (!grpIntervenant.isPresent()) {
            log.warn("La intervention Preventive with ID {} was not found in the DB", preventiveDto.getGroupeIntervenantDto().getId());
            throw new EntityNotFoundException("Aucune intervention Preventive  avec l'ID" + preventiveDto.getGroupeIntervenantDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.INTERVENTION_NOT_FOUND);
        }

        Preventive savePreventive=interventionRepository.save(PreventiveDto.toEntity(preventiveDto));
        return PreventiveDto.fromEntity(savePreventive);
    }

    @Override
    public PreventiveDto getPreventiveById(Long idDto) {
        log.info("We are going to get back the intervention Preventive en fonction de l'ID {} du bien", idDto);
        if(idDto==null){
            log.error("you are provided a null ID for the intervention Preventive");
            return null;
        }
        return preventiveRepository.findById(idDto)
                .map(PreventiveDto::fromEntity)
                .orElseThrow(()->new InvalidEntityException("Aucun intervention Preventive has been found with ID "+idDto,
                        ErrorCodes.INTERVENTION_NOT_FOUND));
    }

    @Override
    public boolean deletePreventive(Long idDto) {
        log.info("We are going to delete a intervention Preventive {}", idDto);
        if (idDto==null){
            log.error("you are provided a null ID for the intervention Preventive");
            return false;
        }
        boolean exist=preventiveRepository.existsById(idDto);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun intervention Preventive avec l'ID = " + idDto + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.INTERVENTION_NOT_FOUND);

        }

        preventiveRepository.deleteById(idDto);
        return true;
    }

    @Override
    public List<PreventiveDto> listOfPreventives() {
        log.info("We are going to take back all intervention Preventive");

        return preventiveRepository.findAll().stream()
                .map(PreventiveDto::fromEntity)
                .collect(Collectors.toList());

    }
}
