package pl.sda.customers.demo;

import org.springframework.stereotype.Component;

@Component
public class OrderService {

    public InvoiceService invoiceService;

    public OrderRepository orderRepository;


    public OrderService(InvoiceService invoiceService, OrderRepository orderRepository) {
        this.invoiceService = invoiceService;
        this.orderRepository = orderRepository;
    }

    public void makeOrder(String number){
        System.out.println("making order: " + number);
        invoiceService.createInvoice(number);
        orderRepository.save(number);
        System.out.println("order created");
    }
}
