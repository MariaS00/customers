package pl.sda.springbeanError;

import org.springframework.stereotype.Component;

@Component
public class BeanB {

    private BeanC beanC;

    public BeanB(BeanC beanC) {
        this.beanC = beanC;
    }

    public void beanB(){
        System.out.println("BBB");
        beanC.beanC();
    }
}
