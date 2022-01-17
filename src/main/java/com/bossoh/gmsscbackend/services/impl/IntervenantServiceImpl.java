package com.bossoh.gmsscbackend.services.impl;
import com.bossoh.gmsscbackend.Dto.IntervenantDto;
import com.bossoh.gmsscbackend.Validator.IntervenantValidator;
import com.bossoh.gmsscbackend.entities.Intervenant;
import com.bossoh.gmsscbackend.entities.Societe;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.repositories.IntervenantRepository;
import com.bossoh.gmsscbackend.repositories.SocieteRepository;
import com.bossoh.gmsscbackend.services.IntervenantService;
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
public class IntervenantServiceImpl implements IntervenantService {

    private final IntervenantRepository intervenantRepository;
    private final SocieteRepository societeRepository;

    @Override
    public IntervenantDto saveIntervenant(IntervenantDto intervDto) {
        log.info("We are going to save a new bien intervenant {}",intervDto);
        List<String> errors= IntervenantValidator.validate(intervDto);
        if(!errors.isEmpty()){
            log.error("l'intervenant' n'est pas valide {}",errors);
            throw new InvalidEntityException("Certain attributs de l'object Intervenant sont null.",
                    ErrorCodes.INTERVENANT_NOT_VALID,errors);
        }
        Optional<Societe> soc = societeRepository.findById(intervDto.getSocieteDto().getId());
        if (!soc.isPresent()) {
            log.warn("La societe with ID {} was not found in the DB", intervDto.getSocieteDto().getId());
            throw new EntityNotFoundException("Aucune société  avec l'ID" + intervDto.getSocieteDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.SOCIETE_NOT_FOUND);
        }

        Intervenant saveIntervenant=intervenantRepository.save(IntervenantDto.toEntity(intervDto));
        return IntervenantDto.fromEntity(saveIntervenant);
    }

    @Override
    public IntervenantDto getIntervenantById(Long idDto) {
        log.info("We are going to get back intervenant en fonction de l'ID {} de intervenant", idDto);
        if(idDto==null){
            log.error("you are provided a null ID for itervenant");
            return null;
        }
        return intervenantRepository.findIntervenantById(idDto)
                .map(IntervenantDto::fromEntity)
                .orElseThrow(()->new InvalidEntityException("Aucun intervenant has been found with ID "+idDto,
                        ErrorCodes.INTERVENANT_NOT_FOUND));
    }

    @Override
    public boolean deleteIntervenant(Long idDto) {
        log.info("We are going to delete a bien immobilier {}", idDto);
        if (idDto==null){
            log.error("you are provided a null ID for the intervenant");
            return false;
        }
        boolean exist=intervenantRepository.existsById(idDto);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun intervenat avec l'ID = " + idDto + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.INTERVENANT_NOT_FOUND);

        }

        intervenantRepository.deleteById(idDto);
        return true;
    }

    @Override
    public List<IntervenantDto> listOfIntervenantsBySocieteId(Long Id) {
        log.info("We are going to take back all inrervenant by society");

        return intervenantRepository.findAll().stream()
                .filter(e -> e.getSociete().getId()==Id)
                .map(IntervenantDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<IntervenantDto> listOfIntervenants() {
        log.info("We are going to take back all inrervenant");

        return intervenantRepository.findAll().stream()
                .map(IntervenantDto::fromEntity)
                .collect(Collectors.toList());
    }

}
