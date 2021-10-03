package pl.sda.customers.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "customer_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Customer {

    @Id
    private UUID id;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)   // jesli bedzie potrzeba zapisania to zapisze
    @JoinColumn(name = "customer_id")
    private List<Address> addresses;

    @Column(name = "customer_type", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    protected Customer(@NonNull String email) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.addresses = new ArrayList<>();
    }

    public void addAddresses(Address address) {
        if (address != null && !addresses.contains(address)){
            addresses.add(address);
        }
    }

    public abstract String getName();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id.equals(customer.id) && email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
