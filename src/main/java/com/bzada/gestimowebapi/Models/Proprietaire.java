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
@DiscriminatorValue("PROPR")
public class Proprietaire extends Utilisateur{
    private int nombreBien;
    private double soldePropietaire;
}
