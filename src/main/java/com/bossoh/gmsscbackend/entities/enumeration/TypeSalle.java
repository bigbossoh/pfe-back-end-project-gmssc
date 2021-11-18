package com.bossoh.gmsscbackend.entities.enumeration;

public enum TypeSalle {

    SALLE_CLASSE("SALLE_CLASSE"),
    BUREAU("BUREAU"),
    AMPHI("AMPHI"),
    SALLE_INFORMATIQUE("SALLE_INFORMATIQUE"),
    SHOW_ROOM("SHOW_ROOM"),
    SECRETARIAT("SECRETARIAT");

    private final String typeSale;

    TypeSalle(String typeSale) {
        this.typeSale = typeSale;
    }

    public String getTypeSale() {
        return typeSale;
    }
}
