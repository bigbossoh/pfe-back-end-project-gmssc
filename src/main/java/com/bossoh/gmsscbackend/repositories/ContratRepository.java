package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContratRepository extends JpaRepository<Contrat,Long> {
    Optional<Contrat> findContratById(Long id);
    Optional<Contrat> findContratByCodeContrat(String codeContrat);
}
