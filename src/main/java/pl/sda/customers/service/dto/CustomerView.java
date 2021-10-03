package pl.sda.customers.service.dto;

import lombok.Value;
import pl.sda.customers.entity.CustomerType;

import java.util.UUID;

@Value
public class CustomerView {

    UUID customerId;
    String name;
    String email;
    CustomerType type;
}
