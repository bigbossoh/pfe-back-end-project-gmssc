package com.bossoh.gmsscbackend.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pieces extends AbstractEntity{
    private String codePiece;
    private String nomPiece;
    private String nomBatiment;
    private String description;
    private int capacitePiece;
    private int positionEtage;
    private String typeSalle;
    @ManyToOne
    @JoinColumn(name="id_bien_immobilier")
    private BienImmobilier bienImmobilier;
//    @OneToMany(mappedBy = "pieces")
//    private List<PieceEquipement> pieceEquipements;


}
