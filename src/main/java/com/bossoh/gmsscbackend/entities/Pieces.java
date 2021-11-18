package com.bossoh.gmsscbackend.entities;

import com.bossoh.gmsscbackend.entities.enumeration.TypeSalle;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pieces {
    @Id
    @GeneratedValue
    private Long id;
    private String codePiece;
    private String nomPiece;
    private String nomBatiment;
    private String description;
    private int capacitePiece;
    private int positionEtage;
    private String typeSalle;
    @ManyToOne
    private BienImmobilier bienImmobilier;
    @OneToMany(mappedBy = "pieces")
    private List<PieceEquipement> pieceEquipements;


}
