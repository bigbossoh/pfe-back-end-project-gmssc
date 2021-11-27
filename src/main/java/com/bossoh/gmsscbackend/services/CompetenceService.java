package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.Dto.CompetenceDto;

import java.util.List;

public interface CompetenceService {

    CompetenceDto saveCompetence(CompetenceDto competenceDto);

    CompetenceDto getCompetenceById(Long idDto);

    boolean deleteCompetence(Long idDto);

    List<CompetenceDto> listOfCompetenceDto();
}
