package com.bossoh.gmsscbackend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.bossoh.gmsscbackend.Dto.SocieteDto;
import com.bossoh.gmsscbackend.entities.BienImmobilier;
import com.bossoh.gmsscbackend.exceptions.InvalidOperationException;
import com.bossoh.gmsscbackend.repositories.BienImmobilierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bossoh.gmsscbackend.Validator.SocieteValidator;
import com.bossoh.gmsscbackend.utils.UtilRandom;
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
	private final BienImmobilierRepository bienImmobilierRepository;
	private final UtilRandom utils;

	@Override
	public SocieteDto saveSociete(SocieteDto socDto) {
		log.info("Enregistrement d'une société {} ",socDto);

		List<String> errors = SocieteValidator.validate(socDto);

		if (!errors.isEmpty()) {
			throw new InvalidEntityExeception("L'objet société ou certain de ses attributs ne peut être null",
					ErrorCodes.SOCIETE_NOT_VALID, errors);
		}
		if(socDto.getId()==null){
			socDto.setCodeSociete(utils.generatedRandomString(6));
		}
		return SocieteDto.fromEntity(societeRepository.save(SocieteDto.toEntity(socDto)));
	}

	@Override
	public SocieteDto getSocieteById(Long id) {
		log.info("we are going to get a society by id {}", id);
		if (id == null) {
		      log.error("Societe ID is null");
		      return null;
		    }
        return societeRepository.findById(id)
				.map(SocieteDto::fromEntity)
				.orElseThrow(()-> new EntityNotFoundException("Aucune societe avec l'ID = " + id + " "
						+ "n' ete trouve dans la BDD",  ErrorCodes.SOCIETE_NOT_FOUND)
		    		);
	}

	@Override
	public boolean deleteSociete(Long id) {
		if(id==null){
			log.error("Societe ID is null");
			return false;
		}
		boolean exist=societeRepository.existsById(id);
		if (!exist)
		{
			throw new EntityNotFoundException("Aucun bien avec l'ID = " + id + " "
					+ "n' ete trouve dans la BDD",  ErrorCodes.SOCIETE_NOT_FOUND);

		}
		List<BienImmobilier> bienImmobiliers=bienImmobilierRepository.findAllBySocieteId(id);
		if (!bienImmobiliers.isEmpty()) {
			throw new InvalidOperationException("Impossible de supprimer une société déjà utilisée",
					ErrorCodes.SOCIETE_ALREADY_IN_USE);
		}
		societeRepository.deleteById(id);
		return true;
	}
	@Override
	public List<SocieteDto> listOfSocietes() {
		 log.info("Liste toutes les sociétés de base de données");
		return societeRepository.findAll()
				.stream()
				.map(SocieteDto::fromEntity)
				.collect(Collectors.toList());
	}

}
