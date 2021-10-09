package pl.sda.customers.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.sda.customers.entity.Customer;
import pl.sda.customers.entity.CustomerRepository;
import pl.sda.customers.service.dto.CustomerDetails;
import pl.sda.customers.service.dto.CustomerView;
import pl.sda.customers.service.exception.CustomerNotExistsException;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
public class CustomerQuery {

    @NonNull
    private final CustomerRepository repository;

    public List<CustomerView> listCustomers(){
        return repository.findAll().stream()
                .map(Customer::toView)
                .collect(toList());
    }

    public CustomerDetails getById(UUID customerId) {

        return repository.findById(customerId)
                .orElseThrow(() -> new CustomerNotExistsException("Customer not found: " + customerId))
                .mapToDetails();

    }

}
