package hello.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope("request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL){
        this.requestURL=requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestURL+"]"+"[log_"+message+"]");
    }

    
    //요청 시 실행됨
    @PostConstruct
    public void init(){
         uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create : "+this);
    }

    //응답 땋 되면 실행됨 
    @PreDestroy
    public void close(){
        System.out.println("["+uuid+"] request scope bean close : "+this);
    }

}
