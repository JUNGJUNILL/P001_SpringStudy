package hello.core.order.scan.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class P001_SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig =new AppConfig();
        MemberService memberService1 =appConfig.memberService();
        MemberService memberService2 =appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1= "+memberService1);
        System.out.println("memberService2= "+memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        P002_SingletonService P002_SingletonService1= P002_SingletonService.getInstance();
        P002_SingletonService P002_SingletonService2= P002_SingletonService.getInstance();

        System.out.println("P002_SingletonService1="+P002_SingletonService1);
        System.out.println("P002_SingletonService2="+P002_SingletonService2);

        //same -> ==
        //equal -> equals
        Assertions.assertThat(P002_SingletonService1).isSameAs(P002_SingletonService1);

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){

        AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 =ac.getBean("memberService",MemberService.class);
        MemberService memberService2 =ac.getBean("memberService",MemberService.class);

        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }




    public static void main(String[] args) {


      }

}
