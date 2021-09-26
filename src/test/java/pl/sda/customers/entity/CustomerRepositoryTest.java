package pl.sda.customers.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    void shouldSave() {
        // given
        final var customer1 = new Person("jan12#wp.pl", "Jan", "Nowak", "1425627282");
        final var customer2 = new Company("com2#wp.pl", "Comp S.A", "2694225");

        // when
        repository.saveAllAndFlush(List.of(customer1, customer2));

        // then
        assertEquals(2, repository.count());
    }

    @Test
    void shouldFindPersonByLastName() {
        // given
        final var customer1 = new Person("jan12#wp.pl", "Jan", "Nowak", "1425627282");
        final var customer2 = new Person("olgi2#wp.pl", "Olgiert", "Mickiewicz", "55598845");
        repository.saveAllAndFlush(List.of(customer1, customer2));

        //when
        final var result = repository.findByLastName("Nowak");

        // then
        assertEquals(List.of(customer1), result);

    }

    @Test
    void shouldFindByFirstNameAndLastNameWithIgnoreCase() {
        // given
        final var customer1 = new Person("jan2#wp.pl", "Jan", "Nowak", "852145863");
        final var customer2 = new Person("jan12#wp.pl", "Jan", "Nowak", "1425627282");
        final var customer3 = new Person("olgi2#wp.pl", "Olgiert", "Mickiewicz", "55598845");
        repository.saveAllAndFlush(List.of(customer1, customer2, customer3));

        // when
        final List<Person> result;
        result = repository.findByFirstNameStartingWithIgnoreCaseAndLastNameStartingWithIgnoreCase("jan", "nowak");

        // then
        assertEquals(List.of(customer1, customer2), result);
    }

    @Test
    void shouldFindAllByEmailIsEndingWithWpPl() {
        // given
        final var customer1 = new Person("jan2@wp.pl", "Jan", "Nowak", "852145863");
        final var customer2 = new Company("com2@wp.pl", "Comp S.A", "2694225");
        final var customer3 = new Person("olgi22@interia.pl", "Olgiert", "Mickiewicz", "55598845");
        repository.saveAllAndFlush(List.of(customer1, customer2, customer3));

        // when
        final var result = repository.findAllByEmailIgnoreCaseIsEndingWith("wp.pl");

        // then
        assertEquals(List.of(customer1, customer2), result);
    }

    @Test
    void shouldSearchPeople() {
        // given
        final var customer1 = new Person("jan2@wp.pl", "Jan", "Nowak", "852145863");
        final var customer2 = new Company("com2@wp.pl", "Comp S.A", "2694225");
        final var customer3 = new Person("olgi22@interia.pl", "Olgiert", "Mickiewicz", "55598845");
        repository.saveAllAndFlush(List.of(customer1, customer2, customer3));

        // when
        final var result = repository.searchPeople("Olgiert", "Mickiewicz");

        // then
        assertEquals(List.of(customer3), result);
    }

    @Test
    void shouldFindCustomersInCity() {
        // given
        final var customer1 = new Person("jan2@wp.pl", "Jan", "Nowak", "852145863");
        final var customer2 = new Company("com2@wp.pl", "Comp S.A", "2694225");
        final var customer3 = new Person("olgi22@interia.pl", "Olgiert", "Mickiewicz", "55598845");

        customer1.addAddresses(new Address("str", "Warszawa", "04-333", "PL"));
        customer2.addAddresses(new Address("str", "Krakow", "08-256", "PL"));
        customer3.addAddresses(new Address("str", "Krakow", "08-256", "PL"));

        repository.saveAllAndFlush(List.of(customer1, customer2, customer3));

        // when
        final var result = repository.findCustomersInCity("Krakow");

        // then
        assertEquals(List.of(customer2, customer3), result);
    }

    @Test
    void shouldFindCompaniesInCountrySortedByName() {
        // given - utwórz kilka firm wraz z adresami i zapisz poprzez repository
        final var customer1 = new Company("com1@wp.pl", "Comp1", "8946451");
        final var customer2 = new Company("com2@wp.pl", "Comp2", "653568542");
        final var customer3 = new Company("com3@wp.pl", "Comp3", "85257451");
        final var customer4 = new Company("com4@wp.pl", "Comp4", "85875478");
        final var customer5 = new Company("com5@wp.pl", "Comp5", "63259652");

        customer1.addAddresses(new Address("str", "Warszawa", "04-333", "PL"));
        customer2.addAddresses(new Address("str", "Krakow", "08-256", "rfvfdc"));
        customer3.addAddresses(new Address("str", "Krakow", "08-256", "PL"));
        customer4.addAddresses(new Address("str", "Krakow", "08-256", "rtfd"));
        customer5.addAddresses(new Address("str", "Krakow", "08-256", "PL"));

        repository.saveAllAndFlush(List.of(customer1, customer2, customer3, customer4, customer5));

        // when - dodaj metodę w repository, która szuka firm w danym kraju np. PL, a rezultaty są posortowane po nazwie firmy
        final var result = repository.findCompaniesInCountry("PL");

        // then - sprawdź czy wyniki się zgadzają z założeniami
        assertEquals(List.of(customer1, customer3, customer5), result);
    }

    @Test
    void shouldFindAllAddressesForLastName() {
        // given - utwórz kilka osób wraz z adresami
        final var customer1 = new Person("ak@wp.pl", "Jan", "Nowak", "92929929929");
        final var customer2 = new Person("qw@wp.pl", "Jan", "Kowalski", "83838288233");
        final var customer3 = new Person("cx@wp.pl", "Janeczek", "Nowaczkiewicz", "83838288233");
        final var customer4 = new Person("er@on.pl", "Mateusz", "Kowalski", "93939939424");

        final var address1 = new Address("str", "Kraków", "33-220", "PL");
        final var address2 = new Address("str", "Wawa", "44-300", "PL");

        customer1.addAddresses(new Address("str", "Wawa", "04-333", "PL"));
        customer2.addAddresses(address1);
        customer2.addAddresses(address2);
        customer3.addAddresses(new Address("str", "Wrocław", "55-200", "PL"));
        final var address3 = new Address("str2", "Kraków", "33-220", "PL");
        customer4.addAddresses(address3);

        repository.saveAllAndFlush(List.of(customer1, customer2, customer3, customer4));

        // when - dodaj metodę w repository, która zwróci wszsytkie adresy pod którymi mieszkają osoby o nazwisku: "Kowalski"
        final var result = repository.findAllAddressesForLastName("Kowalski");

        // then - sprawdź czy wyniki się zgadzają z założeniami
        assertTrue(List.of(address1, address2, address3).containsAll(result));
    }

    @Test
    void shouldCountCustomersByCity() {
        // given - utwórz różnych klientów wraz z adresami
        final var customer1 = new Person("jan2@wp.pl", "Jan", "Nowak", "852145863");
        final var customer2 = new Company("com2@wp.pl", "Comp S.A", "2694225");
        final var customer3 = new Person("olgi22@interia.pl", "Olgiert", "Mickiewicz", "55598845");
        final var customer4 = new Company("com2@wp.pl", "Comp S.A", "2694225");

        customer1.addAddresses(new Address("str", "Warszawa", "04-333", "PL"));
        customer2.addAddresses(new Address("str", "Krakow", "08-256", "PL"));
        customer3.addAddresses(new Address("str", "Krakow", "08-256", "PL"));
        customer4.addAddresses(new Address("str", "Wroclaw", "08-256", "PL"));

        repository.saveAllAndFlush(List.of(customer1, customer2, customer3, customer4));

        // when - napisz query, które zwróci miast + liczbę klientów w danym mieście np.
        // city     |  number_of_customers
        // Warszawa |  2
        // Kraków   |  3
        final var result = repository.countCustomersByCity();

        // then - sprawdź czy wyniki się zgadzają z założeniami
        assertEquals(3, result.size());
        assertArrayEquals(new Object[]{"Krakow", 2L}, result.get(0));
        assertArrayEquals(new Object[]{"Warszawa", 1L}, result.get(1));
        assertArrayEquals(new Object[]{"Wroclaw", 1L}, result.get(2));
    }

    @Test
    void shouldCountCustomersInCountry() {
        //given
        final var customer1 = new Person("jan2@wp.pl", "Jan", "Nowak", "852145863");
        final var customer2 = new Company("com2@wp.pl", "Comp S.A", "2694225");
        final var customer3 = new Person("olgi22@interia.pl", "Olgiert", "Mickiewicz", "55598845");
        final var customer4 = new Company("com2@wp.pl", "Comp S.A", "2694225");

        customer1.addAddresses(new Address("str", "Berlin", "04-333", "DE"));
        customer2.addAddresses(new Address("str", "Krakow", "08-256", "PL"));
        customer3.addAddresses(new Address("str", "Krakow", "08-256", "PL"));
        customer4.addAddresses(new Address("str", "Berlin", "08-256", "DE"));

        repository.saveAllAndFlush(List.of(customer1, customer2, customer3, customer4));

        //when
        final var result = repository.countCustomersByCountyCode();

        //then
        assertEquals(2, result.size());
        final var row1 = result.get(0);
        assertEquals("DE", row1.getCountryCode());
        assertEquals(2, row1.getCount());

        final var row2 = result.get(1);
        assertEquals("PL", row2.getCountryCode());
        assertEquals(2, row2.getCount());
    }

    @Test
    void shouldFindCompaniesWithZipCode() {
        //given
        final var customer1 = new Company("com1@wp.pl", "Comp1", "8946451");
        final var customer2 = new Company("com2@wp.pl", "Comp2", "653568542");
        final var customer3 = new Company("com3@wp.pl", "Comp3", "85257451");
        final var customer4 = new Company("com4@wp.pl", "Comp4", "85875478");
        final var customer5 = new Company("com5@wp.pl", "Comp5", "63259652");

        customer1.addAddresses(new Address("str", "Warszawa", "04-333", "PL"));
        customer2.addAddresses(new Address("str", "Krakow", "09-256", "PL"));
        customer3.addAddresses(new Address("str", "Krakow", "08-256", "PL"));
        customer4.addAddresses(new Address("str", "Krakow", "13-256", "rtfd"));
        customer5.addAddresses(new Address("str", "Krakow", "08-546", "PL"));

        repository.saveAllAndFlush(List.of(customer1, customer2, customer3, customer4, customer5));

        //when
        final var result = repository.findCompaniesWithZipCode("08%");

        //then
        assertTrue(List.of(new CompanyZipCodeView("Comp3", "85257451", "08-256"),
                        new CompanyZipCodeView("Comp5", "63259652", "08-546"))
                .containsAll(result));
    }

    @Test
    void shouldFindPersonViewByEmail() {
        //given
        final var customer1 = new Person("jan2@wp.pl", "Jan", "Nowak", "852145863");
        final var customer2 = new Person("jan2@wp.com", "Jan", "Nowak", "852145863");
        final var customer3 = new Person("olgi22@interia.pl", "Olgiert", "Mickiewicz", "55598845");
        final var customer4 = new Person("olgi22@interia.com", "Olgiert", "Mickiewicz", "55598845");


        repository.saveAllAndFlush(List.of(customer1, customer2, customer3, customer4));

        //when
        final var result = repository.findPersonViewByEmail("%.com");

        //then
        assertTrue(List.of(new PersonView(customer2.getId(), customer2.getEmail(), customer2.getPesel()),
                        new PersonView(customer4.getId(), customer4.getEmail(), customer4.getPesel()))
                .containsAll(result));
    }

    @Test
    void shouldUpdateCountryCodeForCity() {
        // given
        final var company1 = new Company("com@wp.pl", "Januszex", "1234567");
        final var company2 = new Company("squat@wp.pl", "Poltex", "2389232");
        final var company3 = new Company("port@wp.pl", "TylkoPolska", "3459898");
        final var company4 = new Company("gryka@wp.pl", "GrykPol", "9871293471");

        company1.addAddresses(new Address("str", "Dzierżoniów", "23-999", "PL"));
        company2.addAddresses(new Address("str", "Zgorzelec", "32-654", "PL"));
        company3.addAddresses(new Address("str", "Dzierżoniów", "23-098", "DE"));
        company4.addAddresses(new Address("str", "Dzierżoniów", "23-234", "DE"));

        repository.saveAllAndFlush(List.of(company1, company2, company3, company4));

        // when
        final int result = repository.updateCountryCodeForCity("Dzierżoniów", "PL");

        // then
        assertEquals(3, result);
        assertEquals(0, repository.countCityWithCountryCode("Dzierżoniów", "DE"));
    }

    @Test
    void shouldDeleteAllAddressesWithZipCode() {
        // given - przygotowanie danych testowych
        final var company1 = new Company("com@wp.pl", "Januszex", "1234567");
        final var company2 = new Company("squat@wp.pl", "Poltex", "2389232");
        final var company3 = new Company("port@wp.pl", "TylkoPolska", "3459898");
        final var company4 = new Company("gryka@wp.pl", "GrykPol", "9871293471");

        company1.addAddresses(new Address("str", "Dzierżoniów", "23-999", "PL"));
        company2.addAddresses(new Address("str", "Zgorzelec", "32-654", "PL"));
        company3.addAddresses(new Address("str", "Dzierżoniów", "23-999", "DE"));
        company4.addAddresses(new Address("str", "Dzierżoniów", "23-234", "DE"));

        repository.saveAllAndFlush(List.of(company1, company2, company3, company4));

        // when - usuwanie adresów o danym zipCode
        final int result = repository.deleteAllAddressesWithZipCode("23-999");

        // weryfikacja wyników
        assertEquals(2, result);
        assertEquals(0, repository.countAddressesWithZipCode("23-999"));
    }
}
