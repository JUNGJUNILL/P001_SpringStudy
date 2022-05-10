package hello.core.order.scan.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P002_SingletonService {

    private static  final P002_SingletonService instance = new P002_SingletonService();

    public static P002_SingletonService getInstance(){
        return instance;
    }

    //private 생성자
    //new 해서 생성할 수 없게 되버렷!
    private P002_SingletonService(){}

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

    @Test
    @DisplayName("싱글톤인지 확인 해 볼까?")
    void checkSingleton(){
        P002_SingletonService singletonService1= P002_SingletonService.getInstance();
        P002_SingletonService singletonService2= P002_SingletonService.getInstance();

        //싱글톤으로 만들었기 때문에 주소값이 동일하다.
        System.out.println("singletonService1="+singletonService1);
        System.out.println("singletonService2="+singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);

    }

}
