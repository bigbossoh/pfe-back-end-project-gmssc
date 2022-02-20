package com.bzada.gestimowebapi.Services;

import com.bzada.gestimowebapi.Dtos.RegisterRequest;
import com.bzada.gestimowebapi.Exceptions.GestimoWebException;
import com.bzada.gestimowebapi.Models.Locataire;
import com.bzada.gestimowebapi.Models.NotificationEmail;
import com.bzada.gestimowebapi.Models.Utilisateur;
import com.bzada.gestimowebapi.Models.VerificationToken;
import com.bzada.gestimowebapi.Repository.UtilisateurRepository;
import com.bzada.gestimowebapi.Repository.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

import static com.bzada.gestimowebapi.Utils.Constants.ACTIVATION_EMAIL;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class AuthService {
     private UtilisateurRepository utilisateurRepository;
     private PasswordEncoder passwordEncoder;
     private final VerificationTokenRepository verificationTokenRepository;
     private final MailContentBuilder mailContentBuilder;
     private final MailService mailService;

     public void signup(RegisterRequest registerRequest){
         Utilisateur utilisateur=new Locataire();
         utilisateur.setUsername(registerRequest.getUsername());
         utilisateur.setEmail(registerRequest.getEmail());
         utilisateur.setPassword(registerRequest.getPassword());
         utilisateur.setActivated(false);
         utilisateurRepository.save(utilisateur);
         String token=generateVerificationToken(utilisateur);
         String message=mailContentBuilder.build("Merci de vous être enregistré a Gestimoweb, Cliquer sur le lien " +
                 "ci-dessous pour activer votre account: "+ ACTIVATION_EMAIL+"/"+token+"\n");
         mailService.sendMail(new NotificationEmail("Please Activate your account",utilisateur.getEmail(),message));
     }
     private String generateVerificationToken(Utilisateur utilisateur){
         String token= UUID.randomUUID().toString();
         VerificationToken verificationToken= new VerificationToken();
         verificationToken.setToken(token);
         verificationToken.setUtilisateur(utilisateur);
         verificationTokenRepository.save(verificationToken);
         return token;
     }
     private  String encodePassword(String password){
         return passwordEncoder.encode(password);
     }
     public void verifyAccount(String token){
         Optional<VerificationToken> verificationTokenOptional=verificationTokenRepository.findByToken(token);
         verificationTokenOptional.orElseThrow(()->new GestimoWebException("Invalid Token"));
            feachUserAndEnable(verificationTokenOptional.get());
     }
     private void feachUserAndEnable(VerificationToken verificationToken){
         String username=verificationToken.getUtilisateur().getUsername();
         Utilisateur utilisateur=utilisateurRepository.findUtilisateurByUsername(username).orElseThrow(()->
                 new GestimoWebException("Utilisateur avec l'username "+username+" n'exise pas."));
         utilisateur.setActivated(true);
         utilisateurRepository.save(utilisateur);

     }
}
