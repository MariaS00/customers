package pl.sda.customers.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerTest {

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void shouldSaveAddress(){
        //given
        final var customer = new Customer("abs@wp.pl","Jan","Nowak","123");
        //when
        em.persist(customer);
        em.flush();
        em.clear();
        //then
        final var readCustomer = em.find(Customer.class, customer.getId());
        assertEquals(customer, readCustomer);

    }
}