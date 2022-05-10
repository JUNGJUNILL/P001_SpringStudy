package hello.core;


import hello.core.discount.Discount;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//공연기획자
//애플리케이션의 실제 동작이 필요한 구현 객체를 생성한다.
@Configuration
public class AppConfig {

    //@Bean
    //스프링 컨테이너에 등록된다.
    @Bean
    public MemberRepository memberRepository(){
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public Discount discountPolicy(){
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");

        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");

        //return null;
        return new OrderServiceImpl(memberRepository(), discountPolicy());

    }





}
