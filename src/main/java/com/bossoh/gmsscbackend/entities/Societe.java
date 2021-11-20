package com.bossoh.gmsscbackend.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "societe")
public class Societe  {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 5887010445251175940L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "codesociete")
	private String codeSociete;
	@Column(name = "denomination")
	private String denomination;
	@Column(name = "sigle")
	private String sigle;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "datecreationsociete")
	private LocalDate dateCreationSociete;
	@Column(name = "descriptionactivite")
	private String descriptionActivite;

	@Embedded
	private Adresse adresse;

	@Column(name = "codefiscal")
	private String codeFiscal;

	@Column(name = "photo")
	private String photo;

	@Column(name = "email")
	private String email;

	@Column(name = "numtel")
	private String numTel;

	@Column(name = "mobile")
	private String mobile;

	@Column(name = "fax")
	private String fax;

	@Column(name = "siteweb")
	private String steWeb;
	@OneToMany(mappedBy = "societe")
	private List<BienImmobilier> bienImmobiliers;
	@OneToOne
	private Contrat contrat;
}
