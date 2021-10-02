package pl.sda.customers.service;

import com.google.maps.errors.ApiException;
import pl.sda.customers.entity.Address;

import java.io.IOException;

interface ReverseGeocoding {

    class ReverseGeoCodingException extends RuntimeException {

        public ReverseGeoCodingException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    Address reverse(double latitude, double longitude);
}
