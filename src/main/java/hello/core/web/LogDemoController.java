package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.inject.Provider;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final ObjectProvider<MyLogger> myLoggerProvider;
    private final Provider<MyLogger> myLoggerProvider;


    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){

        //MyLogger myLogger=myLoggerProvider.getObject();
        MyLogger myLogger=myLoggerProvider.get();
        //get() 메서드를 통해서 항상 새로운 빈이 생성되는 것을 확인 할 수 있다.
        //get() 메서드를 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다(DL)

        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);
        myLogger.log("Controller test");
        logDemoService.logic("testId");
        return "OK";
    }

}
