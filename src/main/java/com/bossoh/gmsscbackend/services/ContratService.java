package com.bossoh.gmsscbackend.services;
import com.bossoh.gmsscbackend.Dto.ContratDto;
import java.util.List;

public interface ContratService {
    List<ContratDto> listOfContrat();

    ContratDto saveContrat(ContratDto contrat);

    ContratDto getContratId(Long id);

    boolean deleteContrat(Long id);
}
