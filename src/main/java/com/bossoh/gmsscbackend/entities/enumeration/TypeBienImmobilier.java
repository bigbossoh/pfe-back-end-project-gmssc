package com.bossoh.gmsscbackend.entities.enumeration;

public enum TypeBienImmobilier {
	UNIVERSITE("UNIVERSITE"),
	MAISON_INDIVIDUELLE("MAISON_INDIVIDUELLE"),
	VILLA("VILLA");

	private final String typeBienImmobilier;

	TypeBienImmobilier(String typeBienImmobilier) {
		this.typeBienImmobilier = typeBienImmobilier;
	}

	public String getTypeBienImmobilier() {
		return typeBienImmobilier;
	}
}
