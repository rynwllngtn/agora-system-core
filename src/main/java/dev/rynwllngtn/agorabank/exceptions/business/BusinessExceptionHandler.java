package dev.rynwllngtn.agorabank.exceptions.business;

import dev.rynwllngtn.agorabank.exceptions.StandardError;
import dev.rynwllngtn.agorabank.exceptions.business.BusinessException.InsufficientFundsException;
import dev.rynwllngtn.agorabank.exceptions.business.BusinessException.InvalidAmountException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(value = InvalidAmountException.class)
    public ResponseEntity<StandardError> invalidAmount(InvalidAmountException e,
                                                       HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String error = "Valor fornecido é invalido para essa operação!";
        StandardError exception = new StandardError(Instant.now(),
                                                    status.value(),
                                                    error,
                                                    e.getMessage(),
                                                    request.getRequestURI());

        return ResponseEntity.status(status).body(exception);
    }

    @ExceptionHandler(value = InsufficientFundsException.class)
    public ResponseEntity<StandardError> insufficientFunds(InsufficientFundsException e,
                                                           HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String error = "Valor fornecido é insuficiente para essa operação!";
        StandardError exception = new StandardError(Instant.now(),
                                                    status.value(),
                                                    error,
                                                    e.getMessage(),
                                                    request.getRequestURI());

        return ResponseEntity.status(status).body(exception);
    }

}