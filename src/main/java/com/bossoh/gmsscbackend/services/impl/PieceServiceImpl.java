package com.bossoh.gmsscbackend.services.impl;
import com.bossoh.gmsscbackend.Dto.PiecesDto;
import com.bossoh.gmsscbackend.Validator.PieceValidator;
import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.utils.UtilRandom;
import com.bossoh.gmsscbackend.repositories.BienImmobilierRepository;
import com.bossoh.gmsscbackend.repositories.PieceRepository;
import com.bossoh.gmsscbackend.services.PieceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PieceServiceImpl implements PieceService {

    private final PieceRepository pieceRepository;
    private final BienImmobilierRepository bienImmobilierRepository;
    private final UtilRandom utilRandom;

    @Override
    public PiecesDto savePiece(PiecesDto pieceDto) {
        log.info("We are going to save a new piece {}", pieceDto);
        List<String> errors = PieceValidator.validate(pieceDto);
        if (!errors.isEmpty()) {
            log.error("Le bien n'est pas valide {}", errors);
            throw new InvalidEntityException("Certain attributs de l'object piece sont null.",
                    ErrorCodes.PIECE_NOT_VALID, errors);
        }
        Optional<BienImmobilier> bienDto = bienImmobilierRepository.findBienImmobilierById(pieceDto.getBienImmobilierDto().getId());
        if (!bienDto.isPresent()) {
            log.warn("Client with ID {} was not found in the DB", pieceDto.getBienImmobilierDto().getId());
            throw new EntityNotFoundException("Aucun Bien immobilier avec l'ID" + pieceDto.getBienImmobilierDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.BIEN_IMMOBILIER_NOT_FOUND);
        }
        if (pieceDto.getId() == null) {
            pieceDto.setCodePiece(utilRandom.generatedRandomString(6));
        }
            log.info("we are rich that point {}",pieceDto);
        return PiecesDto.fromEntity(pieceRepository.save(PiecesDto.toEntity(pieceDto)));
    }

    @Override
    public PiecesDto getPieceById(Long idDto) {
        log.info("We are going to get back the piece en fonction de l'ID {} de la piece", idDto);
        if (idDto == null) {
            log.error("you are provided a null ID for the piece");
            return null;
        }
        return pieceRepository.findById(idDto)
                .map(PiecesDto::fromEntity)
                .orElseThrow(() -> new InvalidEntityException("Aucune piece has been found with ID " + idDto,
                        ErrorCodes.PIECE_NOT_FOUND));
    }

    @Override
    public PiecesDto getPieceByCode(String codePieceDto) {
        log.info("We are going to get back the piece with code {}", codePieceDto);
        if (!StringUtils.hasLength(codePieceDto)) {
            log.error("you are not provided a code for this piece");
            return null;
        }
        return pieceRepository.findPieceByCodePiece(codePieceDto)
                .map(PiecesDto::fromEntity)
                .orElseThrow(() -> new InvalidEntityException("Aucune piece has been found with Code " + codePieceDto,
                        ErrorCodes.PIECE_NOT_FOUND));
    }

    @Override
    public boolean deletePiece(Long idDto) {
        log.info("We are going to delete a bien Piece {}", idDto);
        if (idDto == null) {
            log.error("you are provided a null ID for the piece");
            return false;
        }
        boolean exist = pieceRepository.existsById(idDto);
        if (!exist) {
            throw new EntityNotFoundException("Aucune piece avec l'ID = " + idDto + " "
                    + "n' ete trouve dans la BDD", ErrorCodes.PIECE_NOT_FOUND);

        }
        //TODO VERIFICATION SI EXISTE UN BIEN AVEC UNE PEIECE
        pieceRepository.deleteById(idDto);
        return true;
    }

    @Override
    public List<PiecesDto> listOfPieces() {
        log.info("We are going to take back all the Piece");

        return pieceRepository.findAll().stream()
                .map(PiecesDto::fromEntity)
                .collect(Collectors.toList());
    }
}