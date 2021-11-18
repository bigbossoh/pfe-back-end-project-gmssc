package com.bossoh.gmsscbackend.entities;

import javax.persistence.*;

import com.bossoh.gmsscbackend.entities.enumeration.TypeBienImmobilier;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class BienImmobilier {
	@Id
	@GeneratedValue
	private Long id;
	private String codeBienImmobilier;
	private String nomBienImmobilier;
	private String typeBienImmobilier;
	private Integer nbreBatiments;
	private String pays;
	private String ville;
	private String rue;
	private String adresse;
	private String mobile;
	private String telephone;
	private String autreInformation;
	private Integer nombrePiece;
	@ManyToOne
	private Societe societe;
	@OneToMany(mappedBy = "bienImmobilier")
	private List<Pieces> pieces;


}
