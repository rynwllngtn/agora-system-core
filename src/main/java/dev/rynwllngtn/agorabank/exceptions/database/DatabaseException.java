package dev.rynwllngtn.agorabank.exceptions.database;

import lombok.Getter;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }

    @Getter
    public static class ResourceNotFoundException extends DatabaseException {

        private String className;

        public ResourceNotFoundException(Class<?> className, Object id) {
            this.className = className.getSimpleName();
            super(String.format("%s com o ID: %s não foi encontrado!", className.getSimpleName(), id));
        }
    }

    public static class UserConstraintException extends DatabaseException {
        public UserConstraintException(String message) {
            super(message);
        }
    }

}