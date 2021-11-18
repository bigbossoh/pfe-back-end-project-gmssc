package com.bossoh.gmsscbackend.exceptions;

import java.util.List;

import lombok.Getter;

public class InvalidEntityExeception extends RuntimeException {
    @Getter
    private ErrorCodes errorCodes;
    @Getter
    private List<String> errors;

    public InvalidEntityExeception(String message) {
        super(message);
    }

    public InvalidEntityExeception(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityExeception(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCodes = errorCode;
    }

    public InvalidEntityExeception(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCodes = errorCode;
    }

    public InvalidEntityExeception(String message, ErrorCodes errorCode,List<String> errors) {
        super(message);
        this.errorCodes = errorCode;
        this.errors=errors;
    }
}