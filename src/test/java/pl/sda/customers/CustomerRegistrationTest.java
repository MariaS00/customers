package pl.sda.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerRegistrationTest {

    @Autowired
    private CustomerRegistration customerRegistration;

    @Test
    void testRegisterCustomer(){
        customerRegistration.register("jan2@wp.pl", "Jan Kowalski");
    }
}