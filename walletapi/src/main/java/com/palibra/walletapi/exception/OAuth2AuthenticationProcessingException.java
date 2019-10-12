package com.palibra.walletapi.exception;

import org.springframework.security.core.AuthenticationException;

@SuppressWarnings("serial")
public class OAuth2AuthenticationProcessingException extends AuthenticationException {
    public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
        super(msg, t);
    }

    public OAuth2AuthenticationProcessingException(String msg) {
        super(msg);
    }
}
