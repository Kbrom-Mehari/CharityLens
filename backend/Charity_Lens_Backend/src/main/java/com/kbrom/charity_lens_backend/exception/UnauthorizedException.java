package com.kbrom.charity_lens_backend.exception;

// for unauthorized activities
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
