package com.bossoh.gmsscbackend.exceptions;

public enum ErrorCodes {
    SOCIETE_NOT_FOUND(1000),
    SOCIETE_NOT_VALID(1001),
    SOCIETE_ALREADY_IN_USE(1100),
    BIEN_IMMOBILIER_NOT_FOUND(2000),
    BIEN_IMMOBILIER_NOT_VALID(2001),
    BIEN_MMOBILIER_ALREADY_IN_USE(2100),
    PIECE_NOT_FOUND(3000),
    PIECE_NOT_VALID(3001),
    EQUIPEMENT_NOT_FOUND(4000),
    EQUIPEMENT_NOT_VALID(4001),
    EQUIPEMENT_PIECE_NOT_FOUND(5000),
    EQUIPEMENT_PIECE_NOT_VALID(5001),
    CONTRAT_NOT_FOUND(6000),
    CONTRAT_NOT_VALID(6001),
    CONTRAT_ALREADY_IN_USE(6100),;

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
