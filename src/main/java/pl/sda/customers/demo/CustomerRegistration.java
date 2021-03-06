package pl.sda.customers.demo;

import org.springframework.stereotype.Component;

@Component
public class CustomerRegistration {

//    @Autowired - NOT RECOMMENDED
    private final CustomerDatabase repository;

//    @Autowired //- not needed although second, empty constructor exist
    public CustomerRegistration(CustomerDatabase repository) {
        this.repository = repository;
    }

//    //empty constructor
//    public CustomerRegistration() {
//    }


    public void register(String email, String name){
        System.out.println("registering the customer:" + email);
        repository.save(email, name);
        System.out.println("registered customer:" + email);
    }

//    Not recommended
//    @Autowired
//    public void setRepository(CustomerRepository repository) {
//        this.repository = repository;
//    }
}
