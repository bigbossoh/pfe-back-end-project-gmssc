package com.bossoh.gmsscbackend.Dto;
import com.bossoh.gmsscbackend.entities.GroupeIntervenant;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class GroupeIntervenantDto {
    private Long id;
    private LocalDate dateAffectation;
    private LocalDate DateFinTravaux;
    private String commentaire;
    private SignalerPanneDto signalerPanneDto;
    public static GroupeIntervenant toEntity(GroupeIntervenantDto dto) {
        if (dto == null) {
            return null;
        }
        GroupeIntervenant newGroupeIntervenant = new GroupeIntervenant();
        newGroupeIntervenant.setId(dto.getId());
        newGroupeIntervenant.setDateAffectation(dto.getDateAffectation());
        newGroupeIntervenant.setCommentaire(dto.getCommentaire());
        newGroupeIntervenant.setDateFinTravaux(dto.getDateFinTravaux());
        newGroupeIntervenant.setSignalerPanne(SignalerPanneDto.toEntity(dto.getSignalerPanneDto()));
        return newGroupeIntervenant;
    }
    public static GroupeIntervenantDto fromEntity(GroupeIntervenant groupeIntervenant) {
        if (groupeIntervenant == null) {
            return null;
        }
        return GroupeIntervenantDto.builder()
                .id(groupeIntervenant.getId())
                .dateAffectation(groupeIntervenant.getDateAffectation())
                .DateFinTravaux(groupeIntervenant.getDateFinTravaux())
                .commentaire(groupeIntervenant.getCommentaire())
                .signalerPanneDto(SignalerPanneDto.fromEntity(groupeIntervenant.getSignalerPanne()))
                .build();
    }
}
