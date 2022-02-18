package com.bossoh.gmsscbackend.services.impl;
import com.bossoh.gmsscbackend.Dto.IntervenantGroupeIntervenantDto;
import com.bossoh.gmsscbackend.Validator.IntervenantGroupeIntervenantValidator;
import com.bossoh.gmsscbackend.entities.GroupeIntervenant;
import com.bossoh.gmsscbackend.entities.Intervenant;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.repositories.GroupeIntervenantRepository;
import com.bossoh.gmsscbackend.repositories.IntervenantGroupeIntervenantRepository;
import com.bossoh.gmsscbackend.repositories.IntervenantRepository;
import com.bossoh.gmsscbackend.services.IntervenantGroupeIntervenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class IntervenantGroupeIntervenantServiceImpl implements IntervenantGroupeIntervenantService {

    private final IntervenantRepository intervenantRepository;
    private final GroupeIntervenantRepository groupeIntervenantRepository;
    private final IntervenantGroupeIntervenantRepository intervenantGroupeIntervenantRepository;



    @Override
    public IntervenantGroupeIntervenantDto saveUtilisateurGroupeIntervenant(IntervenantGroupeIntervenantDto dto) {
        log.info("We are saving a new UtilisateurGroupeIntervenant {} ",dto);
        List<String> errors= IntervenantGroupeIntervenantValidator.validator(dto);
        if(!errors.isEmpty()){
            log.error("L'objet UtilisateurGroupeIntervenant n'est pas valide {}",errors);
            throw new InvalidEntityException("Certain attributs de l'object pieceEquipement sont null.",
                    ErrorCodes.UTILISATEUR_GROUPEINTERVENANT_NOT_VALID,errors);
        }
        Optional<Intervenant> user = intervenantRepository.findById(dto.getIntervenantDto().getId());
        if (!user.isPresent()) {
            log.warn("L'intervenant with ID {} was not found in the DB", dto.getIntervenantDto().getId());
            throw new EntityNotFoundException("Aucun utilisateur avec l'ID" + dto.getIntervenantDto().getId()+
                    " n'a ete trouve dans la BDD",
                    ErrorCodes.INTERVENANT_NOT_FOUND);
        }
        Optional<GroupeIntervenant> grpInterv = groupeIntervenantRepository.findById(dto.getGroupeIntervenantDto().getId());
        if (!grpInterv.isPresent()) {
            log.warn("Le groupe intervenant with ID {} was not found in the DB", dto.getGroupeIntervenantDto().getId());
            throw new EntityNotFoundException("Aucun groupe intervenant avec l'ID" + dto.getGroupeIntervenantDto().getId()+ " n'a ete trouve dans la BDD",
                    ErrorCodes.GROUPEINTERVENANT_NOT_FOUND);
        }

        return IntervenantGroupeIntervenantDto
                .fromEntity(intervenantGroupeIntervenantRepository
                        .save(IntervenantGroupeIntervenantDto.toEntity(dto))
                );
    }
    @Override
    public List<IntervenantGroupeIntervenantDto> listOfUtilisateurGroupeIntervenant() {
        log.info("We are going to take back all the Utilisateur GroupeIntervenant");

        return intervenantGroupeIntervenantRepository.findAll().stream()
                .map(IntervenantGroupeIntervenantDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public IntervenantGroupeIntervenantDto getUtilisateurGroupeIntervenantById(Long id) {
        log.info("We are going to get back the UtilisateurGroupeIntervenant en fonction de l'ID {} du bien", id);
        if(id==null){
            log.error("you are provided a null ID for the Utilisateur GroupeIntervenant");
            return null;
        }
        return intervenantGroupeIntervenantRepository.findById(id)
                .map(IntervenantGroupeIntervenantDto::fromEntity)
                .orElseThrow(()->new InvalidEntityException("Aucun UtilisateurGroupeIntervenant has been found with ID "+id,
                        ErrorCodes.UTILISATEUR_GROUPEINTERVENANT_NOT_FOUND));
    }

    @Override
    public List<IntervenantGroupeIntervenantDto> listOfUtilisateurGroupeIntervenantByGroupeIntervenant(Long idGroupeIntervenant) {
        log.info("We are going to take back all intervenantGroupeIntervenant by GroupeIntervenant {}" , idGroupeIntervenant);

        return intervenantGroupeIntervenantRepository.findAll().stream()
                .filter(e -> Objects.equals(e.getGroupeIntervenant().getId(), idGroupeIntervenant))
                .map(IntervenantGroupeIntervenantDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<Object[]> listOfDistinctGroupeIntervenant() {
        log.info("We are going to take back all the Utilisateur GroupeIntervenant");
        List<Object[]> IntervenantGroupeIntervenantDto=intervenantGroupeIntervenantRepository.getDistinctGroupeIntervenantBy();
        return IntervenantGroupeIntervenantDto;
    }


    @Override
    public boolean deleteUtilisateurGroupeIntervenant(Long id) {
        log.info("We are going to delete a UtilisateurGroupeIntervenant {}", id);
        if (id==null){
            log.error("you are provided a null ID for the UtilisateurGroupeIntervenant");
            return false;
        }
        boolean exist= intervenantGroupeIntervenantRepository.existsById(id);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun UtilisateurGroupeIntervenant avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.UTILISATEUR_GROUPEINTERVENANT_NOT_FOUND);

        }
        intervenantGroupeIntervenantRepository.deleteById(id);
        return true;
    }
}
