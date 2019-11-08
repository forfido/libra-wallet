package com.palibra.walletapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE)
public class ErrorHandlerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

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
