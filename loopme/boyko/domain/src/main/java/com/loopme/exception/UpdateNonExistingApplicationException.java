package com.loopme.exception;

public class UpdateNonExistingApplicationException extends RuntimeException {

    private static final long serialVersionUID = -1217831927956987484L;
    private static final String UPDATE_NON_EXISTING_APPLICATION_MESSAGE = "Application with business key: %s does not exist";

    public UpdateNonExistingApplicationException(String nonExistingApplicationBusinessKey) {
        super(String.format(UPDATE_NON_EXISTING_APPLICATION_MESSAGE, nonExistingApplicationBusinessKey));
    }
}
