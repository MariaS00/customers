package pl.sda.customers.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Customer {

    @Id
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String pesel;

    public Customer(@NonNull String email, @NonNull String firstName, @NonNull String lastName, @NonNull String pesel) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
    }

}
