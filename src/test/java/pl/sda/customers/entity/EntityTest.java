package pl.sda.customers.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
abstract class EntityTest {

    @Autowired
    protected EntityManager em;

    protected void persist(Object entity){
        em.persist(entity);
        em.flush();
        em.clear();
    }
}
