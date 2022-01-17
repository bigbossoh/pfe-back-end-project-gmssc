package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.Dto.BienImmobilierDto;
import com.bossoh.gmsscbackend.Dto.IntervenantDto;

import java.util.List;

public interface IntervenantService {

    IntervenantDto saveIntervenant(IntervenantDto intervDto);
    IntervenantDto getIntervenantById(Long idDto);
    boolean deleteIntervenant(Long idDto);
    List<IntervenantDto> listOfIntervenantsBySocieteId(Long Id);
    List<IntervenantDto> listOfIntervenants();
}
