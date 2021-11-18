package com.bossoh.gmsscbackend.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Adresse implements Serializable {

 

  @Column(name = "pays")
  private String pays;
  
  @Column(name = "ville")
  private String ville;
  
  @Column(name = "rue")
  private String rue;

  @Column(name = "codepostale")
  private String codePostale;

  @Column(name = "adresse1")
  private String adresse1;

  @Column(name = "adresse2")
  private String adresse2;


}