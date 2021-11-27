package com.bossoh.gmsscbackend.services;

import com.bossoh.gmsscbackend.Dto.ChangerMotDePasseUtilisateurDto;
import com.bossoh.gmsscbackend.Dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {


    UtilisateurDto save(UtilisateurDto dto);

    UtilisateurDto findById(Long id);

    List<UtilisateurDto> findAll();

    void delete(Long id);

    UtilisateurDto findByEmail(String email);

    UtilisateurDto changerMotDePasse(ChangerMotDePasseUtilisateurDto dto);
}
