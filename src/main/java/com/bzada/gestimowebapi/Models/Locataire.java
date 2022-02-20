package com.bzada.gestimowebapi.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("LOCAT")
public class Locataire extends Utilisateur{
    private String proffesion;
    private boolean archived;
    private double soldeLocataire;
}
