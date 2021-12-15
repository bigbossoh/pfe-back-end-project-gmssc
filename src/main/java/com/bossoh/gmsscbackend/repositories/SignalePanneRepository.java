package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.SignalerPanne;
import com.bossoh.gmsscbackend.entities.Societe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SignalePanneRepository extends JpaRepository<SignalerPanne,Long> {
    Optional<SignalerPanne> findById(Long Id);
}
