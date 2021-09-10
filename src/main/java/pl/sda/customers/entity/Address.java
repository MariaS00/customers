package pl.sda.customers.entity;

import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@EqualsAndHashCode
public final class Address {

    @Id
    private UUID id;
    //@Column(length = 50)    // NOT Here it is created usually not by hibernate
    private String street;
    private String city;
    private String zipCode;
    private String countryCode;


    public Address(@NonNull String street,@NonNull String city,@NonNull String zipCode,@NonNull String countryCode) {
        this.id = UUID.randomUUID();
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.countryCode = countryCode;
    }

    // only for hibernate
    private Address() {
    }
}
