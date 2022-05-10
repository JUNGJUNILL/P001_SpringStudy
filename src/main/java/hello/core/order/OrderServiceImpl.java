package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.*;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //자동으로 생성자를 만들어주는 역할의 어노테이션
public class OrderServiceImpl implements OrderService{

    //인터페이스에만 의존하도록...(DIP : 추상에만 의존하도록)
    private final MemberRepository memberRepository;
    private final  Discount discountPolicy;


    //생성자가 딱 하나 있으면 @Autowired 생략 할 수 있다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy Discount discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    
// 수정자 주입방식
//    private MemberRepository memberRepository;
//    private  Discount discountPolicy;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(Discount discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice =discountPolicy.discount(member,itemPrice);

        return  new Order(memberId,itemName,itemPrice, discountPrice);
    }

    public MemberRepository getMemberRepository(){
        return memberRepository;
    }

}
