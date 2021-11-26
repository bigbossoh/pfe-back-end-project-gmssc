//package com.bossoh.gmsscbackend.entities;
//
//import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.ObjectIdGenerators;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//
//public class Roles {
//    @Id
//    @GeneratedValue
//    private Long id;
//    private String roleName;
//    @ManyToOne
//    @JoinColumn(name = "idutilisateur")
//    private Utilisateur utilisateur;
//}
