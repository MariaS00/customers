package pl.sda.customers.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Person> findByLastName(String lastName); // --> select * from customers c where c.last_name = ?

    List<Person> findByFirstNameStartingWithIgnoreCaseAndLastNameStartingWithIgnoreCase(String firstName, String lastName);

    List<Customer> findAllByEmailIgnoreCaseIsEndingWith(String email);

    @Query("FROM Person p WHERE UPPER(p.firstName) = UPPER(?1) AND UPPER(p.lastName) = UPPER(?2)")
    List<Person> searchPeople(String firstName, String lastName);

    @Query("FROM Customer c JOIN c.addresses a WHERE UPPER(a.city)= UPPER(?1)")
    List<Customer> findCustomersInCity(String city);

    @Query("SELECT c FROM Company c JOIN c.addresses a WHERE UPPER(a.countryCode) = UPPER(?1) ORDER BY c.name")
    List<Company> findCompaniesInCountry(String country);

    @Query("FROM Person p JOIN p.addresses a WHERE UPPER(p.lastName)= UPPER(?1) GROUP BY a")
    List<Person> findAllAddressesForLastName(String lastName);

    @Query("SELECT c, COUNT (a.city) FROM Customer c JOIN c.addresses a GROUP BY a.city")
    List<Customer> countCustomersByCity();

//    @Query("SELECT COUNT (*) FROM( SELECT COUNT(*) FROM Customer c JOIN c.addresses a GROUP BY a.city)")
//    List<Customer> countCustomersByCity();
}
