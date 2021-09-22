package pl.sda.customers.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    @Test
    void shouldSave(){
        // given
        final var customer1 = new Person("jan12#wp.pl","Jan","Nowak","1425627282");
        final var customer2 = new Company("com2#wp.pl","Comp S.A","2694225");

        // when
        repository.saveAllAndFlush(List.of(customer1, customer2));

        // then
        assertEquals(2, repository.count());
    }

    @Test
    void shouldFindPersonByLastName(){
        // given
        final var customer1 = new Person("jan12#wp.pl","Jan","Nowak","1425627282");
        final var customer2 = new Person("olgi2#wp.pl","Olgiert","Mickiewicz","55598845");
        repository.saveAllAndFlush(List.of(customer1, customer2));

        //when
        final var result = repository.findByLastName("Nowak");

        // then
        assertEquals(List.of(customer1), result);

    }

    @Test
    void shouldFindByFirstNameAndLastNameWithIgnoreCase(){
        // given
        final var customer1 = new Person("jan2#wp.pl","Jan","Nowak","852145863");
        final var customer2 = new Person("jan12#wp.pl","Jan","Nowak","1425627282");
        final var customer3 = new Person("olgi2#wp.pl","Olgiert","Mickiewicz","55598845");
        repository.saveAllAndFlush(List.of(customer1, customer2, customer3));

        // when
        final List<Person> result;
        result = repository.findByFirstNameStartingWithIgnoreCaseAndLastNameStartingWithIgnoreCase("jan", "nowak");

        // then
        assertEquals(List.of(customer1, customer2), result);
    }

    @Test
    void shouldFindAllByEmailIsEndingWithWpPl(){
        // given
        final var customer1 = new Person("jan2@wp.pl","Jan","Nowak","852145863");
        final var customer2 = new Company("com2@wp.pl","Comp S.A","2694225");
        final var customer3 = new Person("olgi22@interia.pl","Olgiert","Mickiewicz","55598845");
        repository.saveAllAndFlush(List.of(customer1, customer2, customer3));

        // when
        final var result = repository.findAllByEmailIgnoreCaseIsEndingWith("wp.pl");

        // then
        assertEquals(List.of(customer1, customer2), result);
    }

    @Test
    void shouldSearchPeople(){
    // given
    final var customer1 = new Person("jan2@wp.pl","Jan","Nowak","852145863");
    final var customer2 = new Company("com2@wp.pl","Comp S.A","2694225");
    final var customer3 = new Person("olgi22@interia.pl","Olgiert","Mickiewicz","55598845");
        repository.saveAllAndFlush(List.of(customer1, customer2, customer3));

    // when
    final var result = repository.searchPeople("Olgiert","Mickiewicz");

    // then
    assertEquals(List.of(customer3), result);
}

    @Test
    void shouldFindCustomersInCity(){
        // given
        final var customer1 = new Person("jan2@wp.pl","Jan","Nowak","852145863");
        final var customer2 = new Company("com2@wp.pl","Comp S.A","2694225");
        final var customer3 = new Person("olgi22@interia.pl","Olgiert","Mickiewicz","55598845");

        customer1.addAddresses(new Address("str","Warszawa","04-333","PL"));
        customer2.addAddresses(new Address("str","Krakow","08-256","PL"));
        customer3.addAddresses(new Address("str","Krakow","08-256","PL"));

        repository.saveAllAndFlush(List.of(customer1, customer2, customer3));

        // when
        final List<Customer> result = repository.findCustomersInCity("Krakow");

        // then
        assertEquals(List.of(customer2, customer3), result);
    }

    @Test
    void shouldFindCompaniesInCountrySortedByName() {
        // given - utwórz kilka firm wraz z adresami i zapisz poprzez repository
            final var customer1 = new Company("com1@wp.pl","Comp1","8946451");
            final var customer2 = new Company("com2@wp.pl","Comp2","653568542");
            final var customer3 = new Company("com3@wp.pl","Comp3","85257451");
            final var customer4 = new Company("com4@wp.pl","Comp4","85875478");
            final var customer5 = new Company("com5@wp.pl","Comp5","63259652");

        customer1.addAddresses(new Address("str","Warszawa","04-333","PL"));
        customer2.addAddresses(new Address("str","Krakow","08-256","rfvfdc"));
        customer3.addAddresses(new Address("str","Krakow","08-256","PL"));
        customer4.addAddresses(new Address("str","Krakow","08-256","rtfd"));
        customer5.addAddresses(new Address("str","Krakow","08-256","PL"));

        repository.saveAllAndFlush(List.of(customer1, customer2, customer3, customer4, customer5));

        // when - dodaj metodę w repository, która szuka firm w danym kraju np. PL, a rezultaty są posortowane po nazwie firmy
         final var result = repository.findCompaniesInCountry("PL");

        // then - sprawdź czy wyniki się zgadzają z założeniami
        assertEquals(List.of(customer1, customer3, customer5), result);
//        fail(); // usuń tą linię jak skończysz test
    }

    @Test
    void shouldFindAllAddressesForLastName() {
        // given - utwórz kilka osób wraz z adresami

        // when - dodaj metodę w repository, która zwróci wszsytkie adresy pod którymi mieszkają osoby o nazwisku: "Kowalski"
        // np.
        // final var result = repository.findAllAddressesForLastName("Kowalski");

        // then - sprawdź czy wyniki się zgadzają z założeniami
        fail(); // usuń tą linię jak skończysz test
    }

    @Test
    void shouldCountCustomersByCity() {
        // given - utwórz różnych klientów wraz z adresami

        // when - napisz query, które zwróci miast + liczbę klientów w danym mieście np.
        // city     |  number_of_customers
        // Warszawa |  2
        // Kraków   |  3
        // final var result = repository.countCustomersByCity();

        // then - sprawdź czy wyniki się zgadzają z założeniami
        fail(); // usuń tą linię jak skończysz test
    }
}
