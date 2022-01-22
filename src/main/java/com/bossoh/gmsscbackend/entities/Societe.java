package com.bossoh.gmsscbackend.entities;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Societe  extends AbstractEntity{
	private String codeSociete;
	private String denomination;
	private String sigle;
	private LocalDate dateCreationSociete;
	private String descriptionActivite;
	private String codeFiscal;
	private String photo;
	private String email;
	private String numTel;
	private String mobile;
	private String fax;
	private String siteWeb;
//	private String villeSociete;
//	private String commune;
//	private String quartier;
	@Embedded
	private Adresse adresse;
	@OneToMany(mappedBy = "societe")
	private List<BienImmobilier> bienImmobiliers;
	@OneToMany(mappedBy = "societe")
	private List<Intervenant> intervenantList;
}
