package hello.core.order.scan.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.assertj.core.api.Assertions;

public class P005_ConfigurationSingletonTest {



    //@Configration과 싱글톤
    @Test
    void configurationTest(){
        ApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);

        MemberRepository memberRepository=ac.getBean("memberRepository",MemberRepository.class);
        MemberServiceImpl memberService =ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService =ac.getBean("orderService",OrderServiceImpl.class);

        MemberRepository memberRepository1= memberService.getMemberRepository();
        MemberRepository memberRepository2= orderService.getMemberRepository();

        System.out.println("MemberServiceImpl->memberRepository1="+memberRepository1);
        System.out.println("OrderServiceImpl->memberRepository2="+memberRepository2);
        System.out.println("memberRepository="+memberRepository);
        //3개 다 같은 객체이다.
        //AppConfig에 @Configration을 떼면 싱글톤을 보장하지 않는다.


        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(orderService.getMemberRepository());

    }


    //@Configuration과 바이트코드 조작의 마법
    @Test
    void configurationDeep(){

        ApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean =ac.getBean(AppConfig.class);

        System.out.println("bean = "+bean.getClass());
    }

    //호출 할 때마다 객체가 새로 생성된다.
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        MemberService memberService1=new AppConfig().memberService();
        MemberService memberService2=new AppConfig().memberService();

        System.out.println("memberService1="+memberService1);
        System.out.println("memberService2="+memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }




}
