package com.bossoh.gmsscbackend.services;
import com.bossoh.gmsscbackend.Dto.IntervenantGroupeIntervenantDto;
import java.util.List;

public interface IntervenantGroupeIntervenantService {

    List<IntervenantGroupeIntervenantDto> listOfUtilisateurGroupeIntervenant();

    IntervenantGroupeIntervenantDto saveUtilisateurGroupeIntervenant(IntervenantGroupeIntervenantDto dto);

    IntervenantGroupeIntervenantDto getUtilisateurGroupeIntervenantById(Long id);

    List<IntervenantGroupeIntervenantDto> listOfUtilisateurGroupeIntervenantByGroupeIntervenant( Long idGroupeIntervenant);

    List<Object[]> listOfDistinctGroupeIntervenant();
    boolean deleteUtilisateurGroupeIntervenant(Long id);
}
