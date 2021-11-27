package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.Competence;
import com.bossoh.gmsscbackend.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompetenceRepository extends JpaRepository<Competence,Long> {
    Optional<Competence> findCompetenceById(Long id);
}
