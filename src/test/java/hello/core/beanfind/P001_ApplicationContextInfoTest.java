package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class P001_ApplicationContextInfoTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {

        String[] beanDefinetionNames = ac.getBeanDefinitionNames();

        for (String beans : beanDefinetionNames) {
            Object bean = ac.getBean(beans);
            System.out.println("name=" + beans + " object=" + bean);
        }
    }


    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {

        String[] beanDefinetionNames = ac.getBeanDefinitionNames();
        for (String beanDefinetionName : beanDefinetionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinetionName);

            //ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            //ROLE_INFRASTRUCTURE : 스프링 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinetionName);
                System.out.println("name=" + beanDefinetionName + " object=" + bean);
            }

        }
    }
}