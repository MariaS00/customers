package pl.sda.customers.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CustomerTest extends EntityTest{

    @Test
    void shouldSaveCompany(){
        //given
        final var company = new Company("abc@wp.pl","Comp S.A","PL987766543");

        //when
        persist(company);

        //then
        final var readCompany = em.find(Company.class, company.getId());
        Assertions.assertEquals(company, readCompany);
    }

    @Test
    void shouldSavePerson(){
        //given
        final var person = new Person("jan1@wp.pl","Jan","Nowak","123456789");

        //when
        persist(person);

        //then
        final var readPerson = em.find(Person.class, person.getId());
        Assertions.assertEquals(person,readPerson);
    }


}