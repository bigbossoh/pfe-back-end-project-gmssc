package com.bossoh.gmsscbackend.exceptions;

public enum ErrorCodes {
    SOCIETE_NOT_FOUND(1000),
    SOCIETE_NOT_VALID(1001),
    SOCIETE_ALREADY_IN_USE(1002),
    BIEN_IMMOBILIER_NOT_FOUND(2000),
    BIEN_IMMOBILIER_NOT_VALID(2001),
    BIEN_MMOBILIER_ALREADY_IN_USE(2002),
    PIECE_NOT_FOUND(3000),
    PIECE_NOT_VALID(3001),
    PIECE_ALREADY_IN_USE(3002),
    EQUIPEMENT_NOT_FOUND(4000),
    EQUIPEMENT_NOT_VALID(4001),
    EQUIPEMENT_PIECE_NOT_FOUND(5000),
    EQUIPEMENT_PIECE_NOT_VALID(5001),
    CONTRAT_NOT_FOUND(6000),
    CONTRAT_NOT_VALID(6001),
    CONTRAT_ALREADY_IN_USE(6002),
    UTILISATEUR_NOT_VALID(7000),
    UTILISATEUR_NOT_FOUND(7001),
    UTILISATEUR_ALREADY_EXISTS(7002),
    COMPETENCE_NOT_VALID(8000),
    COMPETENCE_NOT_FOUND(8001),
    COMPETENCE_ALREADY_EXISTS(8002),
    SIGNALERPANNE_NOT_VALID(9000),
    SIGNALERPANNE_NOT_FOUND(9001),
    SIGNALERPANNE_ALREADY_EXISTS(9002),
    GROUPEINTERVENANT_NOT_VALID(10000),
    GROUPEINTERVENANT_NOT_FOUND(10001),
    GROUPEINTERVENANT_ALREADY_EXISTS(10002),
    UTILISATEUR_GROUPEINTERVENANT_NOT_VALID(11000),
    UTILISATEUR_GROUPEINTERVENANT_NOT_FOUND(11001),
    UTILISATEUR_GROUPEINTERVENANT_ALREADY_EXISTS(11002),
    INTERVENTION_NOT_VALID(12000),
    INTERVENTION_NOT_FOUND(12001),
    INTERVENTION_ALREADY_EXISTS(12002)

    ;
    private int code;
    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
