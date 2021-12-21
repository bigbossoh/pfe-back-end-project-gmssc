package com.bossoh.gmsscbackend.services.auth;

import com.bossoh.gmsscbackend.Dto.UtilisateurDto;
import com.bossoh.gmsscbackend.entities.Utilisateur;
import com.bossoh.gmsscbackend.entities.auth.ExtentedUser;
import com.bossoh.gmsscbackend.exceptions.EntityNotFoundException;
import com.bossoh.gmsscbackend.exceptions.ErrorCodes;
import com.bossoh.gmsscbackend.repositories.UtilisateurRepository;
import com.bossoh.gmsscbackend.services.UtilisateurService;
import com.bossoh.gmsscbackend.services.impl.UtilisateurServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UtilisateurService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto utilisateurDto=service.findByEmail(email);
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        utilisateurDto.getRoles().forEach(rolesDto -> authorities.add(new SimpleGrantedAuthority(rolesDto.getRoleName())));
        return new ExtentedUser(utilisateurDto.getEmail(),utilisateurDto.getMoteDePasse(), authorities);
    }
}
