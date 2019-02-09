package com.loopme.exception;

public class IncompatibleUserRoleException extends RuntimeException {

    private static final long serialVersionUID = -2486389274955657395L;
    private static final String INCOMPATIBLE_USER_ROLE_MESSAGE = "Update performed on the user with incorrect role. User role: %s, but have to be %s";

    public IncompatibleUserRoleException(String currentUserRole, String neededUserRole) {
        super(String.format(INCOMPATIBLE_USER_ROLE_MESSAGE, currentUserRole, neededUserRole));
    }
}
