package com.bossoh.gmsscbackend.services;
import com.bossoh.gmsscbackend.Dto.UtilisateurGroupeIntervenantDto;
import java.util.List;

public interface UtilisateurGroupeIntervenantService {

    List<UtilisateurGroupeIntervenantDto> listOfUtilisateurGroupeIntervenant();

    UtilisateurGroupeIntervenantDto saveUtilisateurGroupeIntervenant(UtilisateurGroupeIntervenantDto dto);

    UtilisateurGroupeIntervenantDto getUtilisateurGroupeIntervenantById(Long id);

    boolean deleteUtilisateurGroupeIntervenant(Long id);
}
