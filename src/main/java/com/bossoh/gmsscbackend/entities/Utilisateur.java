//package com.bossoh.gmsscbackend.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.Instant;
//import java.util.List;
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class Utilisateur {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//    private String nom;
//    private String prenom;
//    private String email;
//    private Instant dateDeNaissance;
//    private String moteDePasse;
//    @Embedded
//    private Adresse adresse;
//    private String photo;
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "utilisateur")
//    @JsonIgnore
//    private List<Roles> roles;
//
//
//}
