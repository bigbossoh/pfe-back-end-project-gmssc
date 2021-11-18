package com.bossoh.gmsscbackend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bossoh.gmsscbackend.entities.Societe;

public interface SocieteRepository extends JpaRepository<Societe, Long> {
	
	Optional<Societe> findById(Long Id);

}
