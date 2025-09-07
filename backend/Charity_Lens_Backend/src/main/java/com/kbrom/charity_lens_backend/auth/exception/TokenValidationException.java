package com.kbrom.charity_lens_backend.auth.exception;

public class TokenValidationException extends RuntimeException{
    public TokenValidationException(String message,Throwable cause) {
        super(message, cause);
    }
}
