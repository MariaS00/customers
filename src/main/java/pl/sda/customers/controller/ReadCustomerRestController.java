package pl.sda.customers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.customers.service.CustomerQuery;
import pl.sda.customers.service.dto.CustomerView;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class ReadCustomerRestController {

    private final CustomerQuery query;

    @GetMapping
    List<CustomerView> getCustomers(){
        return query.listCustomers();
    }

}
