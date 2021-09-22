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

//    @Query("FROM Customer c WHERE c.addresses LIKE(\"[A-Z][a-z]+\"\n,?1,\"[0-9]+\"\n,\"[A-Z][a-z]2\"\n)")
//    List<Customer> findCustomersInCity(String city);

//    @Query(nativeQuery = true, value = "FROM Customer c WHERE c.addresses AND(\"[A-Z][a-z]+\"\n,?1,\"[0-9]+\"\n,\"[A-Z][a-z]2\"\n)")
//    List<Customer> findCustomersInCity(String city);

    @Query("SELECT c FROM Customer c JOIN c.addresses a WHERE UPPER(a.city)= UPPER(?1)")
    List<Customer> findCustomersInCity(String city);

    @Query("SELECT c FROM Company c JOIN c.addresses a WHERE UPPER(a.countryCode) = UPPER(?1) ORDER BY c.name")
    List<Company> findCompaniesInCountry(String country);
}
