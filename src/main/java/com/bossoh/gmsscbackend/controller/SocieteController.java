package com.bossoh.gmsscbackend.controller;
import java.util.List;


import com.bossoh.gmsscbackend.Dto.SocieteDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bossoh.gmsscbackend.entities.Societe;
import com.bossoh.gmsscbackend.services.SocieteService;

import static com.bossoh.gmsscbackend.utils.Constants.APP_ROOT;

@RestController
@RequiredArgsConstructor
@Api(APP_ROOT+"/societes")
public class SocieteController {

	private final SocieteService societeService;

	@GetMapping(value=APP_ROOT+"/societes/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SocieteDto> getListeDesSocietes() {

		return societeService.listOfSocietes();
	}
	@GetMapping(value=APP_ROOT+"/societes/{IdSociete}",produces = MediaType.APPLICATION_JSON_VALUE)
	public SocieteDto getSocieteByID(@PathVariable("IdSociete") Long IdSociete) {
		return societeService.getSocieteById(IdSociete);
	}
	@PostMapping(value =APP_ROOT+"/societes/savesociete",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public SocieteDto saveSociete(@RequestBody  SocieteDto socDto) {

		return societeService.saveSociete(socDto);
	}
	@DeleteMapping(value = APP_ROOT+"/societes/delete/{idSociete}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean deleteSocieteById(@PathVariable("idSociete") Long idSociete) {
		return societeService.deleteSociete(idSociete);
	}
}
