package com.bossoh.gmsscbackend.services;

import java.util.List;

import com.bossoh.gmsscbackend.Dto.SocieteDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bossoh.gmsscbackend.entities.Societe;
@Service
@Transactional
public interface SocieteService {

	SocieteDto saveSociete(SocieteDto socDto);

	SocieteDto getSocieteById(Long id);

	List<SocieteDto> listOfSocietes();

	boolean deleteSociete(Long id);
}
