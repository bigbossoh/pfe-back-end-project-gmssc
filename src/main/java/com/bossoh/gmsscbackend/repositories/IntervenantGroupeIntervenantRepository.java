package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IntervenantGroupeIntervenantRepository extends JpaRepository<IntervenantGroupeIntervenant,Long> {
    Optional<IntervenantGroupeIntervenant> findById(Long id);
    List<IntervenantGroupeIntervenant> findPieceEquipementByIntervenant(Intervenant interv);
    List<IntervenantGroupeIntervenant> findPieceEquipementByGroupeIntervenant(GroupeIntervenant grpInterv);
    @Query("SELECT distinct b.groupeIntervenant FROM IntervenantGroupeIntervenant b")
    List<Object[]> getDistinctGroupeIntervenantBy();
}

