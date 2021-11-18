package com.bossoh.gmsscbackend.services.impl;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bossoh.gmsscbackend.Validator.SocieteValidator;
import com.bossoh.gmsscbackend.utils.UtilRandom;
import com.bossoh.gmsscbackend.entities.Societe;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.exceptions.InvalidEntityExeception;
import com.bossoh.gmsscbackend.repositories.SocieteRepository;
import com.bossoh.gmsscbackend.services.SocieteService;


import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SocieteServiceImpl implements SocieteService {
	private final SocieteRepository societeRepository;
	private final UtilRandom utils;

	@Override
	public Societe saveSociete(Societe soc) {
		log.info("Enregistrement d'une société {} ",soc);

		List<String> errors = SocieteValidator.validate(soc);

		if (!errors.isEmpty()) {
			throw new InvalidEntityExeception("L'objet société ou certain de ses attributs ne peut être null",
					ErrorCodes.SOCIETE_NOT_VALID, errors);
		}
		soc.setCodeSociete(utils.generatedRandomString(6));
		Societe societeSave = societeRepository.save(soc);
		
		return societeSave;
	}

	@Override
	public Societe updateSociete(Societe soc) {
		Optional<Societe> soc1 = societeRepository.findById(soc.getId());
		if (soc1.isPresent()) {
			log.info("Cool La sociéte renseigne existe deja dans la base de bonnée");
			List<String> errors = SocieteValidator.validate(soc);
			if (!errors.isEmpty()) {
				throw new InvalidEntityExeception("L'objet société ou certain de ses attributs ne peut être null",
						ErrorCodes.SOCIETE_NOT_VALID, errors);
			}
			return societeRepository.save(soc);
		}else {
			throw new InvalidEntityExeception("L'objet société ou certain de ses attributs ne peut être null ou n'existe pas",
					ErrorCodes.SOCIETE_NOT_VALID);
		}
	}

	@Override
	public Societe getSocieteById(Long id) {
		log.info("we are going to get a society by id {}", id);
		if (id == null) {
		      log.error("Societe ID is null");
		      return null;
		    }
		    return societeRepository.findById(id).orElseThrow(
		    		()-> new EntityNotFoundException("Aucune societe avec l'ID = " + id + " "
		    		+ "n' ete trouve dans la BDD",  ErrorCodes.SOCIETE_NOT_FOUND)
		    		);
		    		
	}

	@Override
	public boolean deleteSociete(Long id) {
		log.info("Nous supprimons une societ si l'ID de la société existe ");
		boolean exist=societeRepository.existsById(id);
		if (!exist)
		{
			throw new EntityNotFoundException("Aucune societe avec l'ID = " + id + " "
		    		+ "n' ete trouve dans la BDD",  ErrorCodes.SOCIETE_NOT_FOUND);
		    		
		}
		societeRepository.deleteById(id);
		return exist;


	}
	@Override
	public List<Societe> listOfSocietes() {
		 log.info("Liste toutes les sociétés de base de données");
		return societeRepository.findAll();
	}

}
