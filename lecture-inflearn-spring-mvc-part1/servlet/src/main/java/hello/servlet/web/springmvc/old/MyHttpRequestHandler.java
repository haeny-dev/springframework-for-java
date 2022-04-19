package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <HttpRequestHandler 인터페이스를 통한 핸들러 구현시>
 * 1. HandlerMapping -> BeanNameUrlHandlerMapping: 스프링 빈의 이름으로 핸들러를 찾는다.
 * 2. HandlerAdapter -> HttpRequestHandlerAdapter: HttpRequestHandler 처리
 */
@Component("/springmvc/request-handler")
public class MyHttpRequestHandler implements HttpRequestHandler {

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
