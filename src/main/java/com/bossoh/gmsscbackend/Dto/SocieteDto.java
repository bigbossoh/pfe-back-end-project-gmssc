package com.bossoh.gmsscbackend.Dto;
import com.bossoh.gmsscbackend.entities.Societe;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class SocieteDto {
    private Long id;
    private String codeSociete;
    private String denomination;
    private String sigle;
    private LocalDate dateCreationSociete;
    private String descriptionActivite;
    private String codeFiscal;
    private String photo;
    private String email;
    private String numTel;
    private String mobile;
    private String fax;
    private String siteWeb;
    private boolean societeMaintenance;
    private AdresseDto adresse;

    public static SocieteDto fromEntity(Societe societe){
        if(societe==null){
            return null;
        }
        return SocieteDto.builder()
                .id(societe.getId())
                .codeSociete(societe.getCodeSociete())
                .denomination(societe.getDenomination())
                .sigle(societe.getSigle())
                .dateCreationSociete(societe.getDateCreationSociete())
                .descriptionActivite(societe.getDescriptionActivite())
                .codeFiscal(societe.getCodeFiscal())
                .photo(societe.getPhoto())
                .email(societe.getEmail())
                .numTel(societe.getNumTel())
                .mobile(societe.getMobile())
                .fax(societe.getFax())
               .societeMaintenance(societe.isSocieteMaintenance())
                .siteWeb(societe.getSiteWeb())
                .adresse(AdresseDto.fromEntity(societe.getAdresse()))
                .build();
    }
    public static Societe toEntity(SocieteDto dto){
        if (dto==null){
            return null;
        }
        Societe newSociete=new Societe();

        newSociete.setId(dto.getId());
        newSociete.setCodeSociete(dto.getCodeSociete());
        newSociete.setDenomination(dto.getDenomination());
        newSociete.setDescriptionActivite(dto.getDescriptionActivite());
        newSociete.setSigle(dto.getSigle());
        newSociete.setDateCreationSociete(dto.getDateCreationSociete());
        newSociete.setCodeFiscal(dto.getCodeFiscal());
        newSociete.setPhoto(dto.getPhoto());
        newSociete.setNumTel(dto.getNumTel());
        newSociete.setMobile(dto.getMobile());
        newSociete.setFax(dto.getFax());
        newSociete.setEmail(dto.getEmail());
        newSociete.setSocieteMaintenance(dto.isSocieteMaintenance());
        newSociete.setSiteWeb(dto.getSiteWeb());
        newSociete.setAdresse(AdresseDto.toEntity(dto.getAdresse()));
        return newSociete;
    }
}
