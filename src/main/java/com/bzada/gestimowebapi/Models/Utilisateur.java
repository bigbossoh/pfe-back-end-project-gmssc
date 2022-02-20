package com.bzada.gestimowebapi.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 5)
public abstract class Utilisateur extends AbstractEntity {
    private String nom;
    private String prenom;
    private String email;
    private String mobile;
    private LocalDate dateDeNaissance;
    private String lieuNaissance;
    private String typePieceIdentite;
    private String numeroPieceIdentite;
    private LocalDate dateDebutPiece;
    private LocalDate dateFinPiece;
    private String Nationalité;
    private String Genre;
    private boolean isActivated;
    private String username;
    private String password;
}
