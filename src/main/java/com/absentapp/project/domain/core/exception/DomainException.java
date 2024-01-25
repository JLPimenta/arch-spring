package com.absentapp.project.domain.core.exception;

public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public DomainException(String mensagem, Object... params) {
        super(String.format(mensagem, params));
    }
}