package pl.sda.customers.service.exception;

public class PeselAlreadyExistsException extends BusinessServiceException{

    public PeselAlreadyExistsException(String message) {
        super(message);
    }
}
