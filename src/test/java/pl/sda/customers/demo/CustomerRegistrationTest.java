package pl.sda.customers.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.customers.demo.CustomerRegistration;

@SpringBootTest
public class CustomerRegistrationTest {

    @Autowired
    private CustomerRegistration customerRegistration;

    @Test
    void testRegisterCustomer(){
        customerRegistration.register("jan2@wp.pl", "Jan Kowalski");
    }
}
