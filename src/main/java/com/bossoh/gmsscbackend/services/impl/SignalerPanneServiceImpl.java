package com.bossoh.gmsscbackend.services.impl;

import com.bossoh.gmsscbackend.Dto.SignalerPanneDto;
import com.bossoh.gmsscbackend.Validator.SignalerPanneValidator;
import com.bossoh.gmsscbackend.entities.*;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityException;
import com.bossoh.gmsscbackend.repositories.*;
import com.bossoh.gmsscbackend.services.SignalerPanneService;
import com.bossoh.gmsscbackend.utils.UtilRandom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SignalerPanneServiceImpl implements SignalerPanneService {

    private final SignalePanneRepository signalePanneRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final IntervenantRepository intervenantRepository;
    private final EmailSendService emailSendService;
    private final PieceRepository pieceRepository;
    private final UtilRandom utilRandom;

    @Override
    public SignalerPanneDto saveSignalerPanne(SignalerPanneDto Dto) {
        //  DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
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
        Optional<Intervenant> panneIntervenant = intervenantRepository.findById(Dto.getIntervenantDto().getId());
        if (!panneIntervenant.isPresent()) {
            log.warn("L'intervenant with ID {} was not found in the DB", Dto.getIntervenantDto().getId());
            throw new EntityNotFoundException("Aucun intervenant avec l'ID" + Dto.getIntervenantDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.INTERVENANT_NOT_FOUND);
        }
        Optional<Pieces> pannePiece = pieceRepository.findById(Dto.getPiecesDto().getId());
        if (!pannePiece.isPresent()) {
            log.warn("La piece with ID {} was not found in the DB", Dto.getPiecesDto().getId());
            throw new EntityNotFoundException("Aucune piece avec l'ID" + Dto.getPiecesDto().getId() + " n'a ete trouve dans la BDD",
                    ErrorCodes.PIECE_NOT_FOUND);
        }
        if (Dto.getId() == null) {
            Dto.setCodeSignalerPanne(utilRandom.generatedRandomString(6));
        }
        Dto.setDateSignalerPanne(LocalDate.now());
        SignalerPanne saveSignalerPanne = signalePanneRepository.save(SignalerPanneDto.toEntity(Dto));
        SignalerPanneDto savedSignalPanneDto = SignalerPanneDto.fromEntity(saveSignalerPanne);
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(panneIntervenant.get().getEmailIntervenant());
            mailMessage.setCc("bossohpaulin@gmail.com");
            mailMessage.setSubject("Demande d'entretien ou réparation d'équipement: Priorité " + Dto.getPriorite());
            mailMessage.setFrom("michelbossoh@univmetiers.ci");
            mailMessage.setText(
                    "Type de maintenance: " + Dto.getObjetPanne().toUpperCase(Locale.ROOT) + "\n" +
                            "Date de la demande: " + Dto.getDateSignalerPanne() + "\n" +
                            "Etat de la panne: " + Dto.getEtatSignalerPanne().toUpperCase(Locale.ROOT) + "\n" +
                            "Requérant: Nom " + panneUtilisateur.get().getNom() + " Prénom(s): " + panneUtilisateur.get().getPrenom() + "\n" +
                            "Description du problème: " + Dto.getDescriptionPanne() + "\n" +
                            "Salle concerné: " + pannePiece.get().getBienImmobilier().getNomBienImmobilier() + "-" +
                            pannePiece.get().getTypeSalle() + "-" + pannePiece.get().getNomPiece() + "\n" +
                            "Suggestions de réparation ou améliorations à apporter: " + Dto.getSuggestionsAmeliration() + "\n" +
                            "Bonne reception Merci."
            );
            emailSendService.sendMail(mailMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return savedSignalPanneDto;
        //SignalerPanneDto.fromEntity(saveSignalerPanne);


//
    }

    @Override
    public List<SignalerPanneDto> listOfSignalerPanne() {
        log.info("We are going to take back all the signalerPanneDto");

        return signalePanneRepository.findAll().stream()
                .map(SignalerPanneDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SignalerPanneDto> listOfSignalerPanneParOrder() {

        log.info("We are going to take back all the signalerPanneDto by order");

        return signalePanneRepository.findAllByOrderByDateSignalerPanneDesc().stream()
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
