package pl.sda.customers.service.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class AddressView {

    UUID id;
    String street;
    String city;
    String zipCode;
    String countryCode;
}
