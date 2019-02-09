package com.loopme.exception;

public class UpdateNonExistingUserException extends RuntimeException {

    private static final long serialVersionUID = -1146565745815483489L;
    private static final String UPDATE_NON_EXISTING_USER_MESSAGE = "User with business key: %s does not exist";

    public UpdateNonExistingUserException(String nonExistingUserBusinessKey) {
        super(String.format(UPDATE_NON_EXISTING_USER_MESSAGE, nonExistingUserBusinessKey));
    }
}
