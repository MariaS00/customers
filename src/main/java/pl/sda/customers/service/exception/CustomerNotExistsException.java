package pl.sda.customers.service.exception;

public class CustomerNotExistsException extends BusinessServiceException {


    public CustomerNotExistsException(String message) {
        super(message);
    }
}
