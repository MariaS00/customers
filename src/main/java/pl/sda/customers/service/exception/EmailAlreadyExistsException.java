package pl.sda.customers.service.exception;

import lombok.NonNull;

public class EmailAlreadyExistsException extends BusinessServiceException {

    public EmailAlreadyExistsException(String message) {
        super(message);
    }

}
