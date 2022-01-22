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
public class BienImmobilier extends AbstractEntity{

	private String codeBienImmobilier;
	private String nomBienImmobilier;
	private String typeBienImmobilier;
	private Integer nbreBatiments;
	private String Adresses1;
	private String Adresses2;
	private String villeSociete;
	private String commune;
	private String quartier;
	private String mobile;
	private String telephone;
	private String autreInformation;
	private Integer nombrePiece;
	@ManyToOne
	@JoinColumn(name="idsociete")
	private Societe societe;
	@OneToMany(mappedBy = "bienImmobilier")
	private List<Pieces> pieces;
}
