package com.loopme.exception;

public class IncompatibleUserRoleException extends RuntimeException {

    private static final long serialVersionUID = -2486389274955657395L;

    public IncompatibleUserRoleException(String message) {
        super(message);
    }
}
