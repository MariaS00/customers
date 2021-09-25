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

    @Query("SELECT c FROM Company c JOIN c.addresses a WHERE UPPER(a.countryCode) = UPPER(?1) ORDER BY c.name asc")
    List<Company> findCompaniesInCountry(String country);

    @Query("SELECT p.addresses FROM Person p WHERE UPPER(p.lastName) = UPPER(?1)")
    List<Address> findAllAddressesForLastName(String lastName);

    @Query("SELECT a.city, COUNT (c) FROM Customer c INNER JOIN c.addresses a GROUP BY a.city ORDER BY a.city ASC ")
    List<Object[]> countCustomersByCity();

    @Query("SELECT a.countryCode AS countryCode, COUNT (c) AS count FROM Customer c " +
            "INNER JOIN c.addresses a " +
            "GROUP BY a.countryCode " +
            "ORDER BY a.countryCode ASC ")
    List<CountCustomerByCountryCode> countCustomersByCountyCode();

    interface CountCustomerByCountryCode {
        String getCountryCode();
        int getCount();
    }

    @Query("SELECT new pl.sda.customers.entity.CompanyZipCodeView(c.name, c.vat, a.zipCode) " +
            "FROM Company c INNER JOIN c.addresses a WHERE a.zipCode LIKE ?1")
    List<CompanyZipCodeView> findCompaniesWithZipCode(String zipCode);

    @Query("FROM PersonView v WHERE UPPER(v.email) LIKE UPPER(?1)")
    List<PersonView> findPersonViewByEmail(String email);
}
