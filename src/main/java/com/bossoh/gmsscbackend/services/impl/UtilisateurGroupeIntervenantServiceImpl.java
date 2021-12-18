package com.bossoh.gmsscbackend.services.impl;
import com.bossoh.gmsscbackend.Dto.UtilisateurGroupeIntervenantDto;
import com.bossoh.gmsscbackend.Validator.UtilisateurGroupeIntervenantValidator;
import com.bossoh.gmsscbackend.entities.GroupeIntervenant;
import com.bossoh.gmsscbackend.entities.Utilisateur;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.repositories.GroupeIntervenantRepository;
import com.bossoh.gmsscbackend.repositories.UtilisateurGroupeIntervenantRepository;
import com.bossoh.gmsscbackend.repositories.UtilisateurRepository;
import com.bossoh.gmsscbackend.services.UtilisateurGroupeIntervenantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class UtilisateurGroupeIntervenantServiceImpl implements UtilisateurGroupeIntervenantService {

    private final UtilisateurRepository utilisateurRepository;
    private final GroupeIntervenantRepository groupeIntervenantRepository;
    private final UtilisateurGroupeIntervenantRepository utilisateurGroupeIntervenantRepository;



    @Override
    public UtilisateurGroupeIntervenantDto saveUtilisateurGroupeIntervenant(UtilisateurGroupeIntervenantDto dto) {
        log.info("We are saving a new UtilisateurGroupeIntervenant {} ",dto);
        List<String> errors= UtilisateurGroupeIntervenantValidator.validator(dto);
        if(!errors.isEmpty()){
            log.error("L'objet UtilisateurGroupeIntervenant n'est pas valide {}",errors);
            throw new InvalidEntityException("Certain attributs de l'object pieceEquipement sont null.",
                    ErrorCodes.UTILISATEUR_GROUPEINTERVENANT_NOT_VALID,errors);
        }
        Optional<Utilisateur> user = utilisateurRepository.findById(dto.getUtilisateurDto().getId());
        if (!user.isPresent()) {
            log.warn("L'utilisateur with ID {} was not found in the DB", dto.getUtilisateurDto().getId());
            throw new EntityNotFoundException("Aucun utilisateur avec l'ID" + dto.getUtilisateurDto().getId()+ " n'a ete trouve dans la BDD",
                    ErrorCodes.UTILISATEUR_NOT_FOUND);
        }
        Optional<GroupeIntervenant> grpInterv = groupeIntervenantRepository.findById(dto.getGroupeIntervenantDto().getId());
        if (!grpInterv.isPresent()) {
            log.warn("Le groupe intervenant with ID {} was not found in the DB", dto.getGroupeIntervenantDto().getId());
            throw new EntityNotFoundException("Aucun groupe intervenant avec l'ID" + dto.getGroupeIntervenantDto().getId()+ " n'a ete trouve dans la BDD",
                    ErrorCodes.GROUPEINTERVENANT_NOT_FOUND);
        }

        return UtilisateurGroupeIntervenantDto
                .fromEntity(utilisateurGroupeIntervenantRepository
                        .save(UtilisateurGroupeIntervenantDto.toEntity(dto))
                );
    }
    @Override
    public List<UtilisateurGroupeIntervenantDto> listOfUtilisateurGroupeIntervenant() {
        log.info("We are going to take back all the Utilisateur GroupeIntervenant");

        return utilisateurGroupeIntervenantRepository.findAll().stream()
                .map(UtilisateurGroupeIntervenantDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public UtilisateurGroupeIntervenantDto getUtilisateurGroupeIntervenantById(Long id) {
        log.info("We are going to get back the UtilisateurGroupeIntervenant en fonction de l'ID {} du bien", id);
        if(id==null){
            log.error("you are provided a null ID for the Utilisateur GroupeIntervenant");
            return null;
        }
        return utilisateurGroupeIntervenantRepository.findById(id)
                .map(UtilisateurGroupeIntervenantDto::fromEntity)
                .orElseThrow(()->new InvalidEntityException("Aucun UtilisateurGroupeIntervenant has been found with ID "+id,
                        ErrorCodes.UTILISATEUR_GROUPEINTERVENANT_NOT_FOUND));
    }

    @Override
    public boolean deleteUtilisateurGroupeIntervenant(Long id) {
        log.info("We are going to delete a UtilisateurGroupeIntervenant {}", id);
        if (id==null){
            log.error("you are provided a null ID for the UtilisateurGroupeIntervenant");
            return false;
        }
        boolean exist=utilisateurGroupeIntervenantRepository.existsById(id);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun UtilisateurGroupeIntervenant avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.UTILISATEUR_GROUPEINTERVENANT_NOT_FOUND);

        }
        utilisateurGroupeIntervenantRepository.deleteById(id);
        return true;
    }
}
