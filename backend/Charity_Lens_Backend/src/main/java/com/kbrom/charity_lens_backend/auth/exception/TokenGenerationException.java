package com.kbrom.charity_lens_backend.auth.exception;

public class TokenGenerationException extends RuntimeException{
    public TokenGenerationException(String message,Throwable cause) {
        super(message, cause);
    }
}
