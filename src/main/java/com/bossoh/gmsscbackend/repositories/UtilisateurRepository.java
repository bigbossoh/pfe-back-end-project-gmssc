package com.bossoh.gmsscbackend.repositories;

import com.bossoh.gmsscbackend.entities.Societe;
import com.bossoh.gmsscbackend.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {

    // JPQL query
    @Query(value = "select u from Utilisateur u where u.email = :email")
    Optional<Utilisateur> findUtilisateurByEmail(@Param("email") String email);
    Optional<Utilisateur> findUtilisateurById(Long id);
    Optional<Utilisateur> findById(Long Id);
}
