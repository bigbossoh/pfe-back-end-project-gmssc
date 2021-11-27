package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Dto.BienImmobilierDto;
import com.bossoh.gmsscbackend.Dto.CompetenceDto;
import com.bossoh.gmsscbackend.Validator.BienImmobilierValidator;
import com.bossoh.gmsscbackend.Validator.CompetenceValidator;
import com.bossoh.gmsscbackend.entities.*;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.exceptions.InvalidOperationException;
import com.bossoh.gmsscbackend.repositories.CompetenceRepository;
import com.bossoh.gmsscbackend.repositories.UtilisateurRepository;
import com.bossoh.gmsscbackend.services.CompetenceService;
import com.bossoh.gmsscbackend.services.UtilisateurService;
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
public class CompetenceServiceImpl implements CompetenceService {

    private final CompetenceRepository competenceRepository;
    private  final UtilisateurRepository utilisateurRepository;

    @Override
    public CompetenceDto saveCompetence(CompetenceDto competenceDto) {
        log.info("We are going to save a new competence {}",competenceDto);
        List<String> errors= CompetenceValidator.validate(competenceDto);
        if(!errors.isEmpty()){
            log.error("la competence n'est pas valide {}",errors);
            throw new InvalidEntityException("Certain attributs de l'object competence sont null.",
                    ErrorCodes.COMPETENCE_NOT_VALID,errors);
        }
        Optional<Utilisateur> utilisateur = utilisateurRepository.findUtilisateurById(competenceDto.getUserDto().getId());
        if (!utilisateur.isPresent()) {
            log.warn("L'utilisateur with ID {} was not found in the DB", competenceDto.getUserDto().getId());
            throw new EntityNotFoundException("Aucun utilisateur avec l'ID" + competenceDto.getUserDto().getId()+ " n'a ete trouve dans la BDD",
                    ErrorCodes.SOCIETE_NOT_FOUND);
        }

        Competence saveCompetence=competenceRepository.save(CompetenceDto.toEntity(competenceDto));
        return CompetenceDto.fromEntity(saveCompetence);
    }

    @Override
    public CompetenceDto getCompetenceById(Long idDto) {
        log.info("We are going to get back the competence en fonction de l'ID {} de la competence", idDto);
        if(idDto==null){
            log.error("you are provided a null ID for the competence");
            return null;
        }
        return competenceRepository.findCompetenceById(idDto)
                .map(CompetenceDto::fromEntity)
                .orElseThrow(()->new InvalidEntityException("Aucun competence has been found with ID "+idDto,
                        ErrorCodes.COMPETENCE_NOT_FOUND));
    }

    @Override
    public boolean deleteCompetence(Long idDto) {
        log.info("We are going to delete a competence {}", idDto);
        if (idDto==null){
            log.error("you are provided a null ID for the competence");
            return false;
        }
        boolean exist=competenceRepository.existsById(idDto);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucune competence avec l'ID = " + idDto + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.COMPETENCE_NOT_FOUND);
        }
        competenceRepository.deleteById(idDto);
        return true;
    }

    @Override
    public List<CompetenceDto> listOfCompetenceDto() {
        log.info("We are going to take back all the competence");

        return competenceRepository.findAll().stream()
                .map(CompetenceDto::fromEntity)
                .collect(Collectors.toList());
    }
}
