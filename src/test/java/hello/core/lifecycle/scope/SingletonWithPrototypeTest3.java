package hello.core.lifecycle.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototypeTest3 {




    @Test
    void singletonClientUsePrototype(){

        AnnotationConfigApplicationContext ac= new  AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);



    }

    @Scope("singleton")
    @Component
    static class ClientBean{

        @Autowired
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic(){

            //PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
             PrototypeBean prototypeBean = prototypeBeanProvider.get();
             //get() 메서드를 통해서 항상 새로운 프로토타입 빈이 생성되는 것을 확인 할 수 있다.
             //get() 메서드를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다(DL)

            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }

    }



    @Scope("prototype")
    @Component
    static class PrototypeBean{

        private  int count=0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init = "+this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }


    }
}
