package hello.core.web;


import hello.core.common.MyLogger;
import hello.core.common.MyLoggerProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoProxyController {

    private final LogDemoProxyService logDemoService;
    private final MyLoggerProxy myLogger;


    @RequestMapping("log-demo-proxy")
    @ResponseBody
    public String logDemo(HttpServletRequest request){

        String requestURL = request.getRequestURL().toString();
        System.out.println("myLoggerProxy="+ myLogger.getClass());

        myLogger.setRequestURL(requestURL);
        myLogger.log("Controller test");
        logDemoService.logic("testId");
        return "OK proxy";
    }

}
