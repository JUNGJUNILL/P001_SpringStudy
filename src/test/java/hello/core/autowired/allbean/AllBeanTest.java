package hello.core.autowired.allbean;

import hello.core.AutoAppConfig;
import hello.core.discount.Discount;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.assertj.core.api.Assertions;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public class AllBeanTest {
    @Test
    void findAllBean(){
        ApplicationContext ac= new AnnotationConfigApplicationContext(AutoAppConfig.class);

        DiscountService discountService= ac.getBean(DiscountService.class);
        Member member = new Member(1L,"userA", Grade.VIP);
        int discountPrice1= discountService.discount(member,50000, "rateDiscountPolicy");
        int discountPrice2= discountService.discount(member,50000, "fixDiscountPolicy");


        System.out.println("discountPrice1="+discountPrice1);
        System.out.println("discountPrice2="+discountPrice2);
        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice1).isEqualTo(5000);
    }


    @Component
    static class DiscountService{
        private final Map<String, Discount> policyMap;
        private final List<Discount> policies;

        //생성자가 하나라 @Autoweired 생략 가능
        @Autowired
        public DiscountService(Map<String, Discount> policyMap,List<Discount> policies){
            this.policyMap=policyMap;
            this.policies=policies;
            System.out.println("policyMap="+policyMap);
            System.out.println("policies="+policies);
        }


        public int discount(Member member, int price, String discountCode){

            Discount discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }
}
