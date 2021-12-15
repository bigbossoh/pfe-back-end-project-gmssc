package com.bossoh.gmsscbackend.Dto;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
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
    private LocalDate dateResolutionPanne;
    private LocalDate dateAnnulation;
    private String etatSignalerPanne;
    private String causeAnnulation;
    private UtilisateurDto utilisateurDto;
    private PiecesDto piecesDto;
    public static SignalerPanne toEntity(SignalerPanneDto dto) {
        if (dto == null) {
            return null;
        }
        SignalerPanne newSignalerPanne = new SignalerPanne();
        newSignalerPanne.setId(dto.getId());
        newSignalerPanne.setCodeSignalerPanne(dto.getCodeSignalerPanne());
        newSignalerPanne.setDescriptionPanne(dto.getDescriptionPanne());
        newSignalerPanne.setDateSignalerPanne(dto.getDateSignalerPanne());
        newSignalerPanne.setDateResolutionPanne(dto.getDateResolutionPanne());
        newSignalerPanne.setDateAnnulation(dto.getDateAnnulation());
        newSignalerPanne.setEtatSignalerPanne(dto.getEtatSignalerPanne());
        newSignalerPanne.setCauseAnnulation(dto.getCauseAnnulation());
        newSignalerPanne.setUtilisateur(UtilisateurDto.toEntity(dto.getUtilisateurDto()));
        newSignalerPanne.setPieces(PiecesDto.toEntity(dto.getPiecesDto()));
        return newSignalerPanne;
    }
    public static SignalerPanneDto fromEntity(SignalerPanne signalerPanne) {
        if (signalerPanne == null) {
            return null;
        }
        return SignalerPanneDto.builder()
                .id(signalerPanne.getId())
                .codeSignalerPanne(signalerPanne.getCodeSignalerPanne())
                .descriptionPanne(signalerPanne.getDescriptionPanne())
                .dateSignalerPanne(signalerPanne.getDateSignalerPanne())
                .dateResolutionPanne(signalerPanne.getDateResolutionPanne())
                .dateAnnulation(signalerPanne.getDateAnnulation())
                .etatSignalerPanne(signalerPanne.getEtatSignalerPanne())
                .causeAnnulation(signalerPanne.getCauseAnnulation())
                .utilisateurDto(UtilisateurDto.fromEntity(signalerPanne.getUtilisateur()))
                .piecesDto(PiecesDto.fromEntity(signalerPanne.getPieces()))
                .build();
    }
}
