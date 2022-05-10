package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder(){

        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        Member member = new Member(100L,"jji", Grade.VIP);
        memoryMemberRepository.save(member);


        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy());
      orderService.createOrder(100L,"itemA",10000);
    }
}
