package com.payletter.infrastructure.util.exception;

public class ErrorHandlerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ErrorHandlerException() {
    }

    public ErrorHandlerException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public ErrorHandlerException(String errorMessage) {
        super(errorMessage);
    }

    public ErrorHandlerException(Throwable err) {
        super(err);
    }
}
