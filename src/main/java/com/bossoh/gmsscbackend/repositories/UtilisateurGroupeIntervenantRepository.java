package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurGroupeIntervenantRepository extends JpaRepository<UtilisateurGroupeIntervenant,Long> {
    Optional<UtilisateurGroupeIntervenant> findById(Long id);
    List<UtilisateurGroupeIntervenant> findPieceEquipementByUtilisateur(Utilisateur user);
    List<UtilisateurGroupeIntervenant> findPieceEquipementByGroupeIntervenant(GroupeIntervenant grpInterv);
}

