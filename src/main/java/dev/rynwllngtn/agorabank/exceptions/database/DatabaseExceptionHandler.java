package dev.rynwllngtn.agorabank.exceptions.database;

import dev.rynwllngtn.agorabank.exceptions.StandardError;
import dev.rynwllngtn.agorabank.exceptions.database.DatabaseException.ResourceNotFoundException;
import dev.rynwllngtn.agorabank.exceptions.database.DatabaseException.UserConstraintException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class DatabaseExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e,
                                                          HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = String.format("%s não encontrado!", e.getClassName());
        StandardError exception = new StandardError(Instant.now(),
                                                    status.value(),
                                                    error,
                                                    e.getMessage(),
                                                    request.getRequestURI());

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(value = UserConstraintException.class)
    public ResponseEntity<StandardError> userConstraint(UserConstraintException e,
                                                        HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String error = "User have an open account associated!";
        StandardError exception = new StandardError(Instant.now(),
                                                    status.value(),
                                                    error,
                                                    e.getMessage(),
                                                    request.getRequestURI());

        return ResponseEntity.status(status).body(exception);
    }

}