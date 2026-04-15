package dev.rynwllngtn.agorabank.exceptions.business;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public static class InvalidAmountException extends BusinessException {
        public InvalidAmountException(String message) {
            super(message);
        }
    }

    public static class InsufficientFundsException extends BusinessException {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }

}