package com.absentapp.project.domain.core.exception;

public class DomainException extends Exception {

    public DomainException(String s) {
        super(s);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

// TODO: Implementar error handling