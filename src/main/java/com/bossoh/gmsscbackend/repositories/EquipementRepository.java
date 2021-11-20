package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipementRepository extends JpaRepository<Equipement,Long> {

    Optional<Equipement> findEquipementByCodeEquipement(String codeEquipement);

    @Override
    List<Equipement> findAllById(Iterable<Long> longs);
}
