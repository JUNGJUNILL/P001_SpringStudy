package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;

public class BeanLifeCycle {

    @Test
    public void lifeCycleTest(){

        ConfigurableApplicationContext ac =new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        //인터페이스를 활용한 빈 생성주기
       // NetworkClient client =ac.getBean(NetworkClient.class);


        //어노테이션을 활용한 빈 생성주기
        //NetworkClinet2 client2 =ac.getBean(NetworkClinet2.class);


        //어노테이션 @PostConstruct,@PreDestroy
        NetworkClinet3 client3 =ac.getBean(NetworkClinet3.class);

        ac.close();
    }


    @Configuration
    static  class LifeCycleConfig{
        /*
        //인터페이스를 활용한 빈 생성주기
        @Bean
        public NetworkClient networkClinet(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://naver.com");
            return networkClient;
        }
        */

        /*
        //어노테이션을 활용한 빈 생성주기
        @Bean(initMethod = "init",destroyMethod = "close")
        public NetworkClinet2 networkClinet2(){
            NetworkClinet2 networkClinet2 = new NetworkClinet2();
            networkClinet2.setUrl("http://naver22.com");
            return networkClinet2;
        }
        */

        //어노테이션 @PostConstruct,@PreDestroy
        @Bean
        public NetworkClinet3 networkClinet2(){
            NetworkClinet3 networkClinet3 = new NetworkClinet3();
            networkClinet3.setUrl("http://naver33.com");
            return networkClinet3;
        }
    }

}
