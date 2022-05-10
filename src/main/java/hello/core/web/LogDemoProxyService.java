package hello.core.web;


import hello.core.common.MyLogger;
import hello.core.common.MyLoggerProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoProxyService {


    private final MyLoggerProxy myLogger;


    public void logic(String id){
        myLogger.log("service id "+id);
    }



}
