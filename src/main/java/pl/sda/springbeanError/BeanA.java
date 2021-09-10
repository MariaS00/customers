package pl.sda.springbeanError;

import org.springframework.stereotype.Component;

@Component
public class BeanA {

    private BeanB beanB;

    public BeanA(BeanB beanB) {
        this.beanB = beanB;
    }

    public void beanA(){
        System.out.println("AAAA");
        beanB.beanB();
    }
}
