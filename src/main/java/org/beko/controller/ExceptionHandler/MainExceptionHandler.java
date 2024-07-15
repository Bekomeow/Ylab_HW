package org.beko.controller.ExceptionHandler;

import org.beko.dto.AppExceptionResponse;
import org.beko.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * Global exception handler for handling exceptions across the whole application.
 * This class provides methods to handle specific exceptions and return appropriate
 * HTTP responses.
 */
@RestControllerAdvice
public class MainExceptionHandler {


    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<AppExceptionResponse> handleAuthenticationException(AuthenticationException exception) {
        return buildExceptionResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
    }

    @ExceptionHandler(NotValidArgumentException.class)
    ResponseEntity<AppExceptionResponse> handleNotValidArgumentException(NotValidArgumentException exception) {
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(RegisterException.class)
    ResponseEntity<AppExceptionResponse> handleRegisterException(RegisterException exception) {
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<AppExceptionResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    ResponseEntity<AppExceptionResponse> handleInvalidCredentialsException(InvalidCredentialsException exception) {
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(PlaceAlreadyBookedException.class)
    ResponseEntity<AppExceptionResponse> handleWorkspaceAlreadyBookedException(PlaceAlreadyBookedException exception) {
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(PlaceAlreadyExistException.class)
    ResponseEntity<AppExceptionResponse> handleWorkspaceAlreadyExistException(PlaceAlreadyExistException exception) {
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(PlaceNotFoundException.class)
    ResponseEntity<AppExceptionResponse> handleWorkspaceAlreadyExistException(PlaceNotFoundException exception) {
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    ResponseEntity<AppExceptionResponse> handleAccessDeniedException(AccessDeniedException exception) {
        return buildExceptionResponse(HttpStatus.FORBIDDEN, exception.getMessage());
    }

    /**
     * Builds a ResponseEntity with the specified HTTP status and error message.
     * @param status The HTTP status.
     * @param message The error message.
     * @return ResponseEntity with the specified status and AppExceptionResponse body.
     */
    private ResponseEntity<AppExceptionResponse> buildExceptionResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(AppExceptionResponse.builder()
                .status(status.value())
                .message(message)
                .build());
    }
}