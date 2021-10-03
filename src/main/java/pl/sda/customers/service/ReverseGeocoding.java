package pl.sda.customers.service;

import com.google.maps.errors.ApiException;
import pl.sda.customers.entity.Address;
import pl.sda.customers.service.exception.BusinessServiceException;

import java.io.IOException;

interface ReverseGeocoding {

    class ReverseGeocodingException extends RuntimeException {

        public ReverseGeocodingException(String message, Throwable cause) {
            super(message, cause);
        }

        public ReverseGeocodingException(String message) {
            super(message);
        }
    }

    Address reverse(double latitude, double longitude);
}
