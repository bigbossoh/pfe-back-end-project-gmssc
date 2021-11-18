package com.bossoh.gmsscbackend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class PieceEquipement {
    @Id
    @GeneratedValue
    private Long Id;
    private LocalDate dateInstallation;
    @ManyToOne
    private Equipement equipement;
    @ManyToOne
    private Pieces pieces;

}
