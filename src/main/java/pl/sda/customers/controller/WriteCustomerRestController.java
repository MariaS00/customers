package pl.sda.customers.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.customers.service.CustomerAddressService;
import pl.sda.customers.service.CustomerRegistrationService;
import pl.sda.customers.service.dto.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WriteCustomerRestController {

    @NonNull
    private final CustomerRegistrationService registrationService;

    @NonNull
    private final CustomerAddressService addressService;

    @PostMapping("/companies")  // POST -> /api/companies
    ResponseEntity<RegisteredCustomerId> registerCompany(@RequestBody RegisterCompanyForm form){
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.registerCompany(form));
    }

    @PostMapping("/people")     // POST -> /api/people
    ResponseEntity<RegisteredCustomerId> registerPerson(@RequestBody RegisterPersonForm form){
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.registerPerson(form));
    }
    @Value
    static class LatLong{
        double latitude;
        double longitude;
    }


    // latitude + longitude
    // JSON -> {"latitude" : 9854, "longitude" : 7856 }
    @PostMapping("/customers/{customerId}/addresses")           // POST -> /api/customer/{id}/addresses
    ResponseEntity<CreatedAddress> addAddress(@PathVariable("customerId") UUID customerId, @RequestBody LatLong latLong) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(addressService.addAddress(new AddAddressForm(customerId,
                        latLong.getLatitude(), latLong.getLongitude())));
    }
}
