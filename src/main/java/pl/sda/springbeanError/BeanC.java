package pl.sda.springbeanError;

import org.springframework.stereotype.Component;

@Component
public class BeanC {

    private BeanA beanA;

    public BeanC(BeanA beanA) {
        this.beanA = beanA;
    }

    public void beanC(){
        System.out.println("CCCC");
        beanA.beanA();
    }
}
