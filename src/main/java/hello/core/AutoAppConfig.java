package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration


//@ComponentScan : @Component 어노테이션이 붙은 애들을 다 찾아서 자동으로 스프링 빈으로 등록 시켜주는 역할
@ComponentScan(
        basePackages = "hello.core",

        excludeFilters ={ @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class),
                                     //@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,classes = MemberRepository.class)
        }
        //@Configuration 붙은 애들은 빼기 위한 작업
)

public class AutoAppConfig {
    /*
    //동일한 이름가진 빈이 있을경우(수동 등록 된 빈이 오버라이드 된다)
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    */

}
