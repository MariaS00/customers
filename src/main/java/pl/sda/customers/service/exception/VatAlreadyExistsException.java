package pl.sda.customers.service.exception;

import lombok.NonNull;

public class VatAlreadyExistsException extends BusinessServiceException {

    public VatAlreadyExistsException(String message) {
        super(message);
    }
}
