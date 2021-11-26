package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Dto.PieceEquipementDto;
import com.bossoh.gmsscbackend.Validator.PieceEquipementValidator;
import com.bossoh.gmsscbackend.entities.Equipement;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
@Service
@Transactional
public class PieceEquipementServiceImpl implements PieceEquipementService {
    private final PieceEquipementRepository pieceEquipementRepository;
    private final PieceRepository pieceRepository;
    private final EquipementRepository equipementRepository;



    @Override
    public PieceEquipementDto savePieceEquipement(PieceEquipementDto pedto) {
        log.info("We are saving a new pieceEquipement {} ",pedto);
        List<String> errors= PieceEquipementValidator.validator(pedto);
        if(!errors.isEmpty()){
            log.error("L'objet pieceEquipement n'est pas valide {}",errors);
            throw new InvalidEntityExeception("Certain attributs de l'object pieceEquipement sont null.",
                    ErrorCodes.EQUIPEMENT_PIECE_NOT_VALID,errors);
        }
        Optional<Equipement> eqpt = equipementRepository.findById(pedto.getEquipementDto().getId());
        if (!eqpt.isPresent()) {
            log.warn("L'equipement with ID {} was not found in the DB", pedto.getEquipementDto().getId());
            throw new EntityNotFoundException("Aucun equipement avec l'ID" + pedto.getEquipementDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.EQUIPEMENT_NOT_FOUND);
        }
        Optional<Pieces> pc = pieceRepository.findById(pedto.getPiecesDto().getId());
        if (!pc.isPresent()) {
            log.warn("La pièce with ID {} was not found in the DB", pedto.getPiecesDto().getId());
            throw new EntityNotFoundException("Aucune piece avec l'ID" + pedto.getPiecesDto().getId()+ " n'a ete trouve dans la BDD",
                    ErrorCodes.PIECE_NOT_FOUND);
        }

        return PieceEquipementDto
                .fromEntity(pieceEquipementRepository
                        .save(PieceEquipementDto.toEntity(pedto))
                );
    }

    @Override
    public PieceEquipementDto getPieceEquipementyId(Long id) {
        log.info("We are going to get back the Piece Equipement en fonction de l'ID {} du bien", id);
        if(id==null){
            log.error("you are provided a null ID for the Piece Equipement");
            return null;
        }
        return pieceEquipementRepository.findById(id)
                .map(PieceEquipementDto::fromEntity)
                .orElseThrow(()->new InvalidEntityExeception("Aucun bien immobilier has been found with ID "+id,
                        ErrorCodes.EQUIPEMENT_PIECE_NOT_FOUND));
    }

    @Override
    public boolean deletePieceEquipement(Long id) {
        log.info("We are going to delete a Piece Equipement {}", id);
        if (id==null){
            log.error("you are provided a null ID for the Piece Equipement");
            return false;
        }
        boolean exist=pieceEquipementRepository.existsById(id);
        if (!exist)
        {
            throw new EntityNotFoundException("Aucun Piece Equipement avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD",  ErrorCodes.EQUIPEMENT_PIECE_NOT_FOUND);

        }
        pieceEquipementRepository.deleteById(id);
        return true;
    }

    @Override
    public List<PieceEquipementDto> listOfPieceEquipement() {
        log.info("We are going to take back all the PieceEquipent");

        return pieceEquipementRepository.findAll().stream()
                .map(PieceEquipementDto::fromEntity)
                .collect(Collectors.toList());
    }

}
