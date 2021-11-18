package com.bossoh.gmsscbackend.controller;
import java.util.List;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bossoh.gmsscbackend.entities.Societe;
import com.bossoh.gmsscbackend.services.SocieteService;

@RestController
@RequestMapping(path="/api/v1/societe")
@RequiredArgsConstructor
public class SocieteController {
	
	private final SocieteService societeService;

	@GetMapping("/all")
	public List<Societe> getListeDesSocietes() {

		return societeService.listOfSocietes();
	}
	@GetMapping(path = "{IdSociete}")
	public Societe getSocieteByID(@PathVariable("IdSociete") Long IdSociete) {
		return societeService.getSocieteById(IdSociete);
	}
	@PostMapping(path = "/savesociete")
	public Societe saveSociete(@RequestBody  Societe soc) {
		
		return societeService.saveSociete(soc);
	}
	@DeleteMapping(path = "/delete/{idSociete}")
	public boolean deleteSocieteById(@PathVariable("idSociete") Long idSociete) {
		return societeService.deleteSociete(idSociete);
	}
	@PutMapping(path="/update")
	public Societe updatingSociete(@RequestBody Societe soc)

	{		
		return societeService.updateSociete(soc);
	}

}
