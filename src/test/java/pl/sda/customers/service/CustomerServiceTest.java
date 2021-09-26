package pl.sda.customers.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.customers.entity.Company;
import pl.sda.customers.entity.CustomerRepository;
import pl.sda.customers.entity.Person;
import pl.sda.customers.service.dto.RegisterCompanyForm;
import pl.sda.customers.service.dto.RegisterPersonForm;
import pl.sda.customers.service.exception.EmailAlreadyExistsException;
import pl.sda.customers.service.exception.PeselAlreadyExistsException;
import pl.sda.customers.service.exception.VatAlreadyExistsException;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository repository;

    @Test
    void shouldRegisterCompany(){
        //given
        final var companyForm = new RegisterCompanyForm("Comp S.A", "PL123654789", "com1@wp.pl");

        //when
        final var customerId = service.registerCompany(companyForm);

        //then
        assertNotNull(customerId);
        assertTrue(repository.existsById(customerId.getId()));
    }

    @Test
    void shouldNotRegisterCompanyIfEmailExist(){
        //given
        repository.saveAndFlush(new Company("com1@wp.pl", "Test", "12345687"));
        final var companyForm = new RegisterCompanyForm("Comp S.A", "PL123654789", "com1@wp.pl");

        //when and then
        assertThrows(EmailAlreadyExistsException.class, () -> service.registerCompany(companyForm));
    }

    @Test
    void shouldNotRegisterCompanyIfVatExist(){
        //given
        repository.saveAndFlush(new Company("com1@wp.pl", "Test", "PL123654789"));
        final var companyForm = new RegisterCompanyForm("Comp S.A", "PL123654789", "xyv@wp.pl");

        //when and then
        assertThrows(VatAlreadyExistsException.class, () -> service.registerCompany(companyForm));
    }

    @Test
    void shouldRegisterPerson(){
        //given
        final var personForm = new RegisterPersonForm("jan@wp.pl","Jan","Nowak","258963147");

        //when
        final var customerId = service.registerPerson(personForm);

        //then
        assertNotNull(customerId);
        assertTrue(repository.existsById(customerId.getId()));
    }

    @Test
    void shouldNotRegisterPersonIfEmailExist(){
        //given
        repository.saveAndFlush(new Person("jan@wp.pl","Jan","Nowak","258963147"));
        final var personForm = new RegisterPersonForm("jan@wp.pl","Jan","Nowa","25896577");

        //when and then
        assertThrows(EmailAlreadyExistsException.class, () -> service.registerPerson(personForm));
    }

    @Test
    void shouldNotRegisterPersonIfPeselExist(){
        //given
        repository.saveAndFlush(new Person("jan1@wp.pl","Jan","Nowak","258963147"));
        final var personForm = new RegisterPersonForm("jan@wp.pl","Jan","Nowa","258963147");

        //when and then
        assertThrows(PeselAlreadyExistsException.class, () -> service.registerPerson(personForm));
    }

}