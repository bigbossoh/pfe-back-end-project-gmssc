package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Dto.CorrectiveDto;
import com.bossoh.gmsscbackend.entities.Corrective;
import com.bossoh.gmsscbackend.entities.GroupeIntervenant;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.repositories.CorrectiveRepository;
import com.bossoh.gmsscbackend.repositories.GroupeIntervenantRepository;
import com.bossoh.gmsscbackend.repositories.InterventionRepository;
import com.bossoh.gmsscbackend.services.CorrectiveService;
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
public class CorrectiveServiceImpl  implements CorrectiveService {

    private final InterventionRepository interventionRepository;
    private final GroupeIntervenantRepository groupeIntervenantRepository;
    private  final CorrectiveRepository correctiveRepository;

    @Override
    public CorrectiveDto saveCorrective(CorrectiveDto correctiveDto) {
        log.info("We are going to save a new intervention Corrective {}",correctiveDto);
        Optional<GroupeIntervenant> grpIntervenants = groupeIntervenantRepository.findById(correctiveDto.getGroupeIntervenantDto().getId());
        if (!grpIntervenants.isPresent()) {
            log.warn("La intervention Preventive with ID {} was not found in the DB", correctiveDto.getGroupeIntervenantDto().getId());
            throw new EntityNotFoundException("Aucune intervention Preventive  avec l'ID" + correctiveDto.getGroupeIntervenantDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.INTERVENTION_NOT_FOUND);
        }

        Corrective saveCorrective=interventionRepository.save(CorrectiveDto.toEntity(correctiveDto));
        return CorrectiveDto.fromEntity(saveCorrective);
    }

    @Override
    public CorrectiveDto getCorrectiveById(Long idDto) {
        log.info("We are going to get back the intervention Corrective en fonction de l'ID {} du bien", idDto);
        if(idDto==null){
            log.error("you are provided a null ID for the intervention Corrective");
            return null;
        }
        return correctiveRepository.findById(idDto)
                .map(CorrectiveDto::fromEntity)
                .orElseThrow(()->new InvalidEntityException("Aucun intervention Corrective has been found with ID "+idDto,
                        ErrorCodes.INTERVENTION_NOT_FOUND));
    }

    @Override
    public boolean deleteCorrective(Long idDto) {
        log.info("We are going to delete a intervention corrective {}", idDto);
        if (idDto==null){
            log.error("you are provided a null ID for the intervention corrective");
            return false;
        }
        boolean exist=correctiveRepository.existsById(idDto);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun intervention corrective avec l'ID = " + idDto + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.INTERVENTION_NOT_FOUND);

        }

        correctiveRepository.deleteById(idDto);
        return true;
    }

    @Override
    public List<CorrectiveDto> listOfCorrectives() {
        log.info("We are going to take back all intervention corrective");

        return correctiveRepository.findAll().stream()
                .map(CorrectiveDto::fromEntity)
                .collect(Collectors.toList());

    }
}
