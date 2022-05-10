package hello.core.order.scan.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class P004_StatefulServiceTest {



    static  class TestConfig{
        @Bean
        public P003_StatefulService statefulService(){
            return new P003_StatefulService();
        }
    }


    @Test
    void statefulServiceSingleton(){

        ApplicationContext ac=new AnnotationConfigApplicationContext(TestConfig.class);
        P003_StatefulService statefulService1= ac.getBean(P003_StatefulService.class);
        P003_StatefulService statefulService2= ac.getBean(P003_StatefulService.class);

        //스레드 
        statefulService1.order("userA",1000);
        
        //스레드
        statefulService2.order("userB",2000);

        //스프링 빈은 싱글톤이니 statefulService1과 statefulService2는 동일한 참조값을 가진다.
        //그러므로 필드가 공유된다..
        int price = statefulService1.getPrice();
        System.out.println("userA="+price);
        Assertions.assertThat(statefulService1).isEqualTo(statefulService2);

        System.out.println("----------------------");

        int price1= statefulService1.order2("userA",1000);
        int price2= statefulService2.order2("userB",2000);

        System.out.println("price1="+price1+" price2="+price2);

    }

}
