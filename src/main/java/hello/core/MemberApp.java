package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//        AppConfig appConfig=new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //스프링 컨테이너
        ApplicationContext ac=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService= ac.getBean("memberService",MemberService.class);

        Member member =new Member(1l,"memberA", Grade.VIP);
        memberService.join(member);

       Member findmember = memberService.findMember(1l);

        System.out.println(member.getName());
        System.out.println(findmember.getName());

    }
}
