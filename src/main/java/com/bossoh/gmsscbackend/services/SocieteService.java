package com.bossoh.gmsscbackend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bossoh.gmsscbackend.entities.Societe;
@Service
@Transactional
public interface SocieteService {
	
	Societe saveSociete(Societe soc);
	
	Societe updateSociete(Societe soc);

	Societe getSocieteById(Long id);
	
	boolean deleteSociete(Long id);

	List<Societe> listOfSocietes();

}
