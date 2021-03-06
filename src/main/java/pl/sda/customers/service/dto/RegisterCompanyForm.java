package pl.sda.customers.service.dto;

import lombok.NonNull;
import lombok.Value;

@Value
public class RegisterCompanyForm {

    @NonNull
    String name;
    @NonNull
    String vat;
    @NonNull
    String email;

}
