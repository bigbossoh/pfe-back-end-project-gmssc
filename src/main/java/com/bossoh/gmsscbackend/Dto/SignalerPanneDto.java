package com.bossoh.gmsscbackend.Dto;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.entities.Intervenant;
import com.bossoh.gmsscbackend.entities.SignalerPanne;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class SignalerPanneDto {
    private Long id;
    private String codeSignalerPanne;
    private String objetPanne;
    private String descriptionPanne;
    private LocalDate dateSignalerPanne;
    private String priorite;
    private LocalDate dateResolutionPanne;
    private LocalDate dateAnnulation;
    private String etatSignalerPanne;
    private String causeAnnulation;
    private String suggestionsAmeliration;
    private UtilisateurDto utilisateurDto;
    private PiecesDto piecesDto;
    private IntervenantDto intervenantDto;
    public static SignalerPanne toEntity(SignalerPanneDto dto) {
        if (dto == null) {
            return null;
        }
        SignalerPanne newSignalerPanne = new SignalerPanne();
        newSignalerPanne.setId(dto.getId());
        newSignalerPanne.setObjetPanne(dto.getObjetPanne());
        newSignalerPanne.setCodeSignalerPanne(dto.getCodeSignalerPanne());
        newSignalerPanne.setDescriptionPanne(dto.getDescriptionPanne());
        newSignalerPanne.setPriorite(dto.getPriorite());
        newSignalerPanne.setSuggestionsAmeliration(dto.getSuggestionsAmeliration());
        newSignalerPanne.setDateSignalerPanne(dto.getDateSignalerPanne());
        newSignalerPanne.setDateResolutionPanne(dto.getDateResolutionPanne());
        newSignalerPanne.setDateAnnulation(dto.getDateAnnulation());
        newSignalerPanne.setEtatSignalerPanne(dto.getEtatSignalerPanne());
        newSignalerPanne.setCauseAnnulation(dto.getCauseAnnulation());
        newSignalerPanne.setUtilisateur(UtilisateurDto.toEntity(dto.getUtilisateurDto()));
        newSignalerPanne.setPieces(PiecesDto.toEntity(dto.getPiecesDto()));
        newSignalerPanne.setIntervenant(IntervenantDto.toEntity(dto.getIntervenantDto()));
        return newSignalerPanne;
    }
    public static SignalerPanneDto fromEntity(SignalerPanne signalerPanne) {
        if (signalerPanne == null) {
            return null;
        }
        return SignalerPanneDto.builder()
                .id(signalerPanne.getId())
                .objetPanne(signalerPanne.getObjetPanne())
                .codeSignalerPanne(signalerPanne.getCodeSignalerPanne())
                .descriptionPanne(signalerPanne.getDescriptionPanne())
                .priorite(signalerPanne.getPriorite())
                .suggestionsAmeliration((signalerPanne.getSuggestionsAmeliration()))
                .dateSignalerPanne(signalerPanne.getDateSignalerPanne())
                .dateResolutionPanne(signalerPanne.getDateResolutionPanne())
                .dateAnnulation(signalerPanne.getDateAnnulation())
                .etatSignalerPanne(signalerPanne.getEtatSignalerPanne())
                .causeAnnulation(signalerPanne.getCauseAnnulation())
                .utilisateurDto(UtilisateurDto.fromEntity(signalerPanne.getUtilisateur()))
                .piecesDto(PiecesDto.fromEntity(signalerPanne.getPieces()))
                .intervenantDto(IntervenantDto.fromEntity(signalerPanne.getIntervenant()))
                .build();
    }
}
