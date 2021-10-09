package pl.sda.customers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.sda.customers.entity.Customer;
import pl.sda.customers.service.CustomerQuery;
import pl.sda.customers.service.dto.CustomerDetails;
import pl.sda.customers.service.dto.CustomerView;
import pl.sda.customers.service.exception.CustomerNotExistsException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
//@RequestMapping(value = "/api/customers/{id}", method = RequestMethod.GET)
@RequiredArgsConstructor
public class ReadCustomerRestController {

    private final CustomerQuery query;
//    private final CustomerView customer;

    @GetMapping
    List<CustomerView> getCustomers() {
        return query.listCustomers();
    }


    // /api/customers/{id}  -> JSON { type:"PERSON", "firstName": "Jan", ... , "email": "ad@d.pl", addresses: []}
    //                      -> JSON { type:"COMPANY","name" : "asa", "vat" : "5665", "email" : "...", addresses: [] }

    @GetMapping("/{customerId}")
    CustomerDetails getCustomerDetails(@PathVariable UUID customerId ){
        return query.getById(customerId);
    }

//    public String getPathWithId(@PathVariable("id") long id) {
//        return String.valueOf(id);
//    }
}
