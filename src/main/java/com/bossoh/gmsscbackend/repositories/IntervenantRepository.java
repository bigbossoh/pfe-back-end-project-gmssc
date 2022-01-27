package com.bossoh.gmsscbackend.repositories;


import com.bossoh.gmsscbackend.entities.Intervenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IntervenantRepository extends JpaRepository<Intervenant,Long> {

    Optional<Intervenant> findIntervenantById(Long idInterv);
    List<Intervenant> findAllBySocieteIdAndFonctionInterv(Long idSociete, String fonctionInterv);
    List<Intervenant> findAllByFonctionInterv( String fonctionInterv);
}
