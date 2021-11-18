package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Validator.PieceEquipementValidator;
import com.bossoh.gmsscbackend.utils.UtilRandom;
import com.bossoh.gmsscbackend.entities.Equipement;
import com.bossoh.gmsscbackend.entities.PieceEquipement;
import com.bossoh.gmsscbackend.entities.Pieces;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityExeception;
import com.bossoh.gmsscbackend.repositories.EquipementRepository;
import com.bossoh.gmsscbackend.repositories.PieceEquipementRepository;
import com.bossoh.gmsscbackend.repositories.PieceRepository;
import com.bossoh.gmsscbackend.services.PieceEquipementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.time.LocalTime.now;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class PieceEquipementServiceImpl implements PieceEquipementService {
    private final PieceEquipementRepository pieceEquipementRepository;
    private final UtilRandom utilRandom;
    private final PieceRepository pieceRepository;
    private final EquipementRepository equipementRepository;
    @Override
    public List<PieceEquipement> listOfPieceEquipement() {
        return pieceEquipementRepository.findAll();
    }

    @Override
    public PieceEquipement savePieceEquipement(PieceEquipement pieceEquipement) {
        log.info("We are saving a new pieceEquipement {} ",pieceEquipement);
        List<String> errors= PieceEquipementValidator.validator(pieceEquipement);
        log.error(String.valueOf(errors));
        if(!errors.isEmpty()){
            throw new InvalidEntityExeception("L'objet bien pieceEquipement possede certains de ses attributs null",
                    ErrorCodes.EQUIPEMENT_PIECE_NOT_VALID,errors);
        }
        log.info(pieceEquipement.getEquipement().getId().toString());
        Optional<Equipement> eqpt=equipementRepository.findById(pieceEquipement.getEquipement().getId());
        if(eqpt.isPresent()){
            pieceEquipement.setEquipement(eqpt.get());
        }else
        {
            throw new InvalidEntityExeception("L'objet equipement possede certains de ses attributs null",
                    ErrorCodes.EQUIPEMENT_NOT_FOUND);
        }
        log.info(pieceEquipement.getPieces().getId().toString());
        Optional<Pieces> pcs=pieceRepository.findById(pieceEquipement.getPieces().getId());
        if(pcs.isPresent()){
            pieceEquipement.setPieces(pcs.get());
        }else
        {
            throw new InvalidEntityExeception("L'objet Piece possede certains de ses attributs null",
                    ErrorCodes.PIECE_NOT_FOUND);
        }

        pieceEquipement.setDateInstallation(LocalDate.now());
        return pieceEquipementRepository.save(pieceEquipement);
    }

    @Override
    public PieceEquipement updatePieceEquipement(PieceEquipement pieceEquipement) {
        log.info("We are going to update a existing PieceEquipement");
        Optional<PieceEquipement> pEqt= pieceEquipementRepository.findById(pieceEquipement.getId());
        if(pEqt.isPresent()){
            log.info("The PieceEquipement is well existing...");
            List<String> errors= PieceEquipementValidator.validator(pieceEquipement);
            if(!errors.isEmpty()){
                throw new InvalidEntityExeception("L'objet piece immobilier possede certains de ses attributs null",
                        ErrorCodes.EQUIPEMENT_PIECE_NOT_VALID,errors);
            }
            return pieceEquipementRepository.save(pieceEquipement);
        }else {
            throw new InvalidEntityExeception("L'objet piece immobilier doesn't exist in the BD",
                    ErrorCodes.EQUIPEMENT_PIECE_NOT_FOUND);
        }
    }

    @Override
    public PieceEquipement getPieceEquipementyId(Long id) {
        log.info("We are going to get back a piece equipement by ID {}",id);

        if (id == null) {
            log.error("pieceEquipement  ID is null");
            return null;
        }
        return pieceEquipementRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Aucun bien avec l'ID = " + id + " "
                        + "n' ete trouve dans la BDD",  ErrorCodes.EQUIPEMENT_PIECE_NOT_FOUND)
        );
    }

    @Override
    public boolean deletePieceEquipement(Long id) {
        log.info("Nous supprimons un bien si l'ID de la PieceEquipement existe ");
        boolean exist=pieceEquipementRepository.existsById(id);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun bien avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.EQUIPEMENT_PIECE_NOT_FOUND);

        }
        pieceEquipementRepository.deleteById(id);
        return true;
    }
}
