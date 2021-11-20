package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.entities.Contrat;
import com.bossoh.gmsscbackend.entities.Equipement;

import java.util.List;

public interface ContratService {
    List<Contrat> listOfContrat();

    Contrat saveContrat(Contrat contrat);

    Contrat updateContrat(Contrat contrat);

    Contrat getContratId(Long id);

    Contrat getContratByCode(String codeContrat);

    boolean deleteEquipement(Long id);
}
