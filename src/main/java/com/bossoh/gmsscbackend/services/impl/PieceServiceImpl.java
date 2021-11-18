package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Validator.PieceValidator;
import com.bossoh.gmsscbackend.defaultData.UtilRandom;
import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.Pieces;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityExeception;
import com.bossoh.gmsscbackend.repositories.BienImmobilierRepository;
import com.bossoh.gmsscbackend.repositories.PieceRepository;
import com.bossoh.gmsscbackend.services.PieceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PieceServiceImpl implements PieceService {

    private final PieceRepository pieceRepository;
    private final BienImmobilierRepository bienImmobilierRepository;
    private final UtilRandom utilRandom;

    @Override
    public List<Pieces> listOfPieces() {
        log.info("we going to retreive all the pieces");
        return pieceRepository.findAll();
    }

    @Override
    public Pieces savePiece(Pieces pieces) {
        log.info("We are going to save a new piece {}", pieces);
        List<String> errors= PieceValidator.validate(pieces);
       if(!errors.isEmpty()){
            throw new InvalidEntityExeception("Lapiece possede certains de ses attributs qui sont null",
                    ErrorCodes.PIECE_NOT_VALID,errors);
       }
       Optional<BienImmobilier>bienImmo= bienImmobilierRepository.findById(pieces.getBienImmobilier().getId());
       if(bienImmo.isPresent()){
           pieces.setBienImmobilier(bienImmo.get());
       } else{
           throw new InvalidEntityExeception("L'objet bien immobilier possede certains de ses attributs qui sont null",
                   ErrorCodes.BIEN_IMMOBILIER_NOT_FOUND,errors);
       }
        pieces.setCodePiece(utilRandom.generatedRandomString(6));
       return pieceRepository.save(pieces);

    }

    @Override
    public Pieces updatePiece(Pieces pieces) {
        log.info("We are going to update a existing piece");
        Optional<Pieces> piecetest= pieceRepository.
                findById(pieces.getId());
        if(piecetest.isPresent()){
            log.info("The piece is well existing...");
            List<String> errors= PieceValidator.validate(pieces);
            if(!errors.isEmpty()){
                throw new InvalidEntityExeception("L'objet piece possede certains de ses attributs null",
                        ErrorCodes.PIECE_NOT_VALID,errors);
            }
            return pieceRepository.save(pieces);
        }else {
            throw new InvalidEntityExeception("L'objet piece doesn't exist in the BD",
                    ErrorCodes.PIECE_NOT_FOUND);
        }
    }

    @Override
    public Pieces getPieceById(Long id) {
        log.info("We are going to get back a piece by ID {}",id);

        if (id == null) {
            log.error("bien immobilier ID is null");
            return null;
        }
        return pieceRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("Aucune pièce avec l'ID = " + id + " "
                        + "n' ete trouve dans la BDD",  ErrorCodes.PIECE_NOT_FOUND)
        );
    }

    @Override
    public Pieces getPieceByCode(String codePiece) {
        log.info("We are going to get back a piece by code piece {}",codePiece);

        if (codePiece == null) {
            log.error("le code de la piece is null");
            return null;
        }
        return pieceRepository.findPieceByCodePiece(codePiece).orElseThrow(
                ()-> new EntityNotFoundException("Aucune pièce avec le code = " + codePiece + " "
                        + "n' ete trouve dans la BDD",  ErrorCodes.PIECE_NOT_FOUND)
        );
    }

    @Override
    public boolean deletePiece(Long id) {
        log.info("Nous supprimons une piece si l'ID de la société existe ");
        boolean exist=pieceRepository.existsById(id);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucune piece avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.PIECE_NOT_FOUND);

        }
        pieceRepository.deleteById(id);
        return true;
    }
}
