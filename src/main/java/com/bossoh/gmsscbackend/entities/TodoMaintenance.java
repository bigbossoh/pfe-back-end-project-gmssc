package com.bossoh.gmsscbackend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class TodoMaintenance extends AbstractEntity{
    private String titre;
    private String description;
    private Integer quantite;
    private String unite;
    private LocalDate dateDoto;
    private String lieuGeographique;
    private double coutMaintenance;
    @ManyToOne
    private Corrective corrective;
}
