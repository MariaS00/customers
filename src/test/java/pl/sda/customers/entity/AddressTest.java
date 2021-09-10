package pl.sda.customers.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressTest {

    @Autowired
    private EntityManager em;

    @Test
    @Transactional
    void shouldSaveAddress(){
        //given
        final var address = new Address("str","Wawa","01-200","PL");
        //final var address equal final Address address

        //when  // save to db
        em.persist(address);   // add to cache
        
        // just for test in result select
        em.flush();     //insert into addresses .......
        em.clear();     // clear cache

        //then
        final var readAddress = em.find(Address.class, address.getId());    // select a.* from addresses
        assertEquals(address,readAddress);
    }

}