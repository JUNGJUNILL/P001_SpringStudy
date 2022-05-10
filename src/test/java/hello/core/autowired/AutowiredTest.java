package hello.core.autowired;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class AutowiredTest {


    @Test
    void AutowiredOption(){

       ApplicationContext ac =new  AnnotationConfigApplicationContext(TestBean.class);

    }


    //@Autowired 옵션 처리
    @Component
    static  class TestBean{

//        @Bean
//        public Member setMember(){
//            return new Member(100l,"jji", Grade.BASIC);
//        }

        @Autowired(required = false)
        public void setNoBean1(Member member1){
            System.out.println("member1="+member1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member2){
            System.out.println("member2="+member2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member3){
            System.out.println("member3="+member3);
        }

    }
}
