package pl.sda.customers.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.sda.customers.demo.OrderService;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
   private OrderService orderService;

    @Test
    void testOrderService(){
        orderService.makeOrder("122315468");
    }
}
