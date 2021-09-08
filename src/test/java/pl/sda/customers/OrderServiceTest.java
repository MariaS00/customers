package pl.sda.customers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
   private OrderService orderService;

    @Test
    void testOrderService(){
        orderService.makeOrder("122315468");
    }
}
