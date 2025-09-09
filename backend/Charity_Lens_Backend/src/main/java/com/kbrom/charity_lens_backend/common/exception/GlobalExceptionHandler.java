package com.kbrom.charity_lens_backend.common.exception;

import com.kbrom.charity_lens_backend.auth.exception.InvalidCredentialsException;
import com.kbrom.charity_lens_backend.auth.exception.TokenGenerationException;
import com.kbrom.charity_lens_backend.auth.exception.TokenValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
    return buildErrorResponse(HttpStatus.NOT_FOUND,e.getMessage());
}
@ExceptionHandler(TokenGenerationException.class)
public ResponseEntity<ErrorResponse> handleTokenGenerationException(TokenGenerationException e) {
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
}
@ExceptionHandler(DuplicateEntryException.class)
public ResponseEntity<ErrorResponse> handleDuplicateEntryException(DuplicateEntryException e) {
    return buildErrorResponse(HttpStatus.CONFLICT,e.getMessage());
}
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return buildErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
}
@ExceptionHandler(InvalidCredentialsException.class)
public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException e){
    return buildErrorResponse(HttpStatus.UNAUTHORIZED,e.getMessage());
}
@ExceptionHandler(TokenValidationException.class)
public ResponseEntity<ErrorResponse> handleTokenValidationException(TokenValidationException e) {
    return buildErrorResponse(HttpStatus.UNAUTHORIZED,e.getMessage());
}
@ExceptionHandler(BadRequestException.class)
public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException e) {
    return buildErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage());
}
@ExceptionHandler(UnauthorizedException.class)
public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException e) {
    return buildErrorResponse(HttpStatus.UNAUTHORIZED,e.getMessage());
}
private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status,String message){
    ErrorResponse response=new ErrorResponse(status.value(),message,status.getReasonPhrase(), LocalDateTime.now());
    return ResponseEntity.status(status)
                         .body(response);
}

}
