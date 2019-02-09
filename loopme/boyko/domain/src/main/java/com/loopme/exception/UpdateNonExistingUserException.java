package com.loopme.exception;

public class UpdateNonExistingUserException extends RuntimeException {
    public UpdateNonExistingUserException(String message) {
        super(message);
    }
}
