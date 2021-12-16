package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Dto.BienImmobilierDto;
import com.bossoh.gmsscbackend.Validator.BienImmobilierValidator;
import com.bossoh.gmsscbackend.entities.Pieces;
import com.bossoh.gmsscbackend.exceptions.InvalidOperationException;
import com.bossoh.gmsscbackend.repositories.PieceRepository;
import com.bossoh.gmsscbackend.utils.UtilRandom;
import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.Societe;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.repositories.BienImmobilierRepository;
import com.bossoh.gmsscbackend.repositories.SocieteRepository;
import com.bossoh.gmsscbackend.services.BienImmobilierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BienImmobilierServiceImpl implements BienImmobilierService {
    private final BienImmobilierRepository bienImmobilierRepository;
    private final SocieteRepository societeRepository;
    private final PieceRepository pieceRepository;
    private final UtilRandom utilRandom;

    @Override
    public BienImmobilierDto saveBienImmobilier(BienImmobilierDto bienDto) {
        log.info("We are going to save a new bien immobilier {}",bienDto);
        List<String> errors= BienImmobilierValidator.validate(bienDto);
        if(!errors.isEmpty()){
            log.error("le bien immobilier n'est pas valide {}",errors);
            throw new InvalidEntityException("Certain attributs de l'object bien immobilier sont null.",
                    ErrorCodes.BIEN_IMMOBILIER_NOT_VALID,errors);
        }
        Optional<Societe> soc = societeRepository.findById(bienDto.getSocieteDto().getId());
        if (!soc.isPresent()) {
            log.warn("La societe with ID {} was not found in the DB", bienDto.getSocieteDto().getId());
            throw new EntityNotFoundException("Aucune société  avec l'ID" + bienDto.getSocieteDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.SOCIETE_NOT_FOUND);
        }
        if(bienDto.getId() ==null){
            bienDto.setCodeBienImmobilier(utilRandom.generatedRandomString(6));
        }
        BienImmobilier saveBien=bienImmobilierRepository.save(BienImmobilierDto.toEntity(bienDto));
        return BienImmobilierDto.fromEntity(saveBien);
    }

    @Override
    public BienImmobilierDto getBienImmobilierById(Long idDto) {
        log.info("We are going to get back the bien immobilier en fonction de l'ID {} du bien", idDto);
        if(idDto==null){
            log.error("you are provided a null ID for the bien immobilier");
            return null;
        }
        return bienImmobilierRepository.findBienImmobilierById(idDto)
                .map(BienImmobilierDto::fromEntity)
                .orElseThrow(()->new InvalidEntityException("Aucun bien immobilier has been found with ID "+idDto,
                        ErrorCodes.BIEN_IMMOBILIER_NOT_FOUND));
    }

    @Override
    public BienImmobilierDto getBienImmobilierByCode(String codeBienDto) {
        log.info("We are going to get back the bien immobilire with code {}",codeBienDto);
        if (!StringUtils.hasLength(codeBienDto)){
            log.error("you are not provided a code for this bien immobilier");
            return  null;
        }
        return bienImmobilierRepository.findBienImmobilierByCodeBienImmobilier(codeBienDto)
                .map(BienImmobilierDto::fromEntity)
                .orElseThrow(()->new InvalidEntityException("Aucun bien immobilier has been found with Code "+codeBienDto,
                        ErrorCodes.SOCIETE_NOT_FOUND));
    }

    @Override
    public boolean deleteBienImmobilier(Long idDto) {
        log.info("We are going to delete a bien immobilier {}", idDto);
        if (idDto==null){
            log.error("you are provided a null ID for the bien immobilier");
            return false;
        }
        boolean exist=bienImmobilierRepository.existsById(idDto);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun bien avec l'ID = " + idDto + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.BIEN_IMMOBILIER_NOT_FOUND);

        }
        List<Pieces> bienImmobiliers=pieceRepository.findAllByBienImmobilierId(idDto);
        if (!bienImmobiliers.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer un bien immobilié déjà utilisé",
                    ErrorCodes.BIEN_MMOBILIER_ALREADY_IN_USE);
        }
        bienImmobilierRepository.deleteById(idDto);
        return true;
    }

    @Override
    public List<BienImmobilierDto> listOfBienImmobiliers() {
        log.info("We are going to take back all the bien immobilier");

        return bienImmobilierRepository.findAll().stream()
                .map(BienImmobilierDto::fromEntity)
                .collect(Collectors.toList());
    }
}
