package com.bossoh.gmsscbackend.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Adresse implements Serializable {

  private String pays;
  private String ville;
  private String rue;
  private String codePostale;
  private String adresse1;
  private String adresse2;

}