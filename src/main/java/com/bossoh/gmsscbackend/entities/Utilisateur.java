package com.bossoh.gmsscbackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {

    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private Instant dateDeNaissance;
    private String moteDePasse;
    @Embedded
    private Adresse adresse;
    private String photo;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "utilisateur")
    @JsonIgnore
    private List<Roles> roles=new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Competence> competenceList;


}
