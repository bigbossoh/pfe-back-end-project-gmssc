package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Dto.SignalerPanneDto;
import com.bossoh.gmsscbackend.Validator.SignalerPanneValidator;
import com.bossoh.gmsscbackend.entities.*;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.repositories.PieceRepository;
import com.bossoh.gmsscbackend.repositories.SignalePanneRepository;
import com.bossoh.gmsscbackend.repositories.UtilisateurRepository;
import com.bossoh.gmsscbackend.services.SignalerPanneService;
import com.bossoh.gmsscbackend.utils.UtilRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SignalerPanneServiceImpl implements SignalerPanneService {

    private final SignalePanneRepository signalePanneRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final PieceRepository pieceRepository;
    private final UtilRandom utilRandom;

    @Override
    public SignalerPanneDto saveSignalerPanne(SignalerPanneDto Dto) {
        log.info("We are going to save a new signalerpanne {}", Dto);
        List<String> errors = SignalerPanneValidator.Validate(Dto);
        if (!errors.isEmpty()) {
            log.error("le signalement d'une panne n'est pas valide {}", errors);
            throw new InvalidEntityException("Certain attributs de l'object signalerPanne sont null.",
                    ErrorCodes.SIGNALERPANNE_NOT_VALID, errors);
        }
        Optional<Utilisateur> panneUtilisateur = utilisateurRepository.findById(Dto.getUtilisateurDto().getId());
        if (!panneUtilisateur.isPresent()) {
            log.warn("L'utilisateur with ID {} was not found in the DB", Dto.getUtilisateurDto().getId());
            throw new EntityNotFoundException("Aucun utilisateur avec l'ID" + Dto.getUtilisateurDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.UTILISATEUR_NOT_FOUND);
        }

        Optional<Pieces> pannePiece = pieceRepository.findById(Dto.getPiecesDto().getId());
        if (!pannePiece.isPresent()) {
            log.warn("La piece with ID {} was not found in the DB", Dto.getPiecesDto().getId());
            throw new EntityNotFoundException("Aucune piece avec l'ID" +  Dto.getPiecesDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.PIECE_NOT_FOUND);
        }
        if (Dto.getId() == null) {
            Dto.setCodeSignalerPanne(utilRandom.generatedRandomString(6));
        }
        Dto.setDateSignalerPanne(LocalDate.now());
        SignalerPanne saveSignalerPanne = signalePanneRepository.save(SignalerPanneDto.toEntity(Dto));
        return SignalerPanneDto.fromEntity(saveSignalerPanne);
    }

    @Override
    public List<SignalerPanneDto> listOfSignalerPanne() {
        log.info("We are going to take back all the signalerPanneDto");

        return signalePanneRepository.findAll().stream()
                .map(SignalerPanneDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SignalerPanneDto getSignalerPanneId(Long id) {
        log.info("We are going to get back the signalisation of panne en fonction de l'ID {} de la panne", id);
        if (id == null) {
            log.error("you are provided a null ID for the SignalerPanne");
            return null;
        }
        return signalePanneRepository.findById(id)
                .map(SignalerPanneDto::fromEntity)
                .orElseThrow(() -> new InvalidEntityException("Aucun signalement de panne has been found with ID " + id,
                        ErrorCodes.SIGNALERPANNE_NOT_FOUND));
    }

    @Override
    public boolean deleteSignalerPanne(Long id) {
        log.info("We are going to delete a signalement of panne {}", id);
        if (id == null) {
            log.error("you are provided a null ID for the signalerPanne");
            return false;
        }
        boolean exist = signalePanneRepository.existsById(id);
        if (!exist) {
            throw new EntityNotFoundException("Aucun signalement de panne avec l'ID = " + id + " "
                    + "n' ete trouve dans la BDD", ErrorCodes.SIGNALERPANNE_NOT_FOUND);
        }
        Optional<SignalerPanne> sp = signalePanneRepository.findById(id);
        if (!sp.isPresent()) {
            log.warn("Le signalement de panne with ID {} was not found in the DB", sp);
            throw new EntityNotFoundException("Aucun signalement de panne avec l'ID" + sp + " n'a ete trouve dans la BDD",
                    ErrorCodes.SIGNALERPANNE_NOT_FOUND);
        }

        signalePanneRepository.deleteById(id);
        return true;
    }
}
