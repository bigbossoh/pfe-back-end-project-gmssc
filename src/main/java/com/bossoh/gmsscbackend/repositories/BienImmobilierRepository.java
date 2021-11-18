package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.BienImmobilier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BienImmobilierRepository extends JpaRepository<BienImmobilier,Long> {
    Optional<BienImmobilier> findBienImmobilierById(Long idBien);
    Optional<BienImmobilier> findBienImmobilierByCodeBienImmobilier(String codeBien);
}
