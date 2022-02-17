package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <Controller 인터페이스를 통한 핸들러 구현시>
 * 1. HandlerMapping -> BeanNameUrlHandlerMapping: 스프링 빈의 이름으로 핸들러를 찾는다.
 * 2. HandlerAdapter -> SimpleControllerHandlerAdapter: Controller 인터페이스 처리
 */
@Component(value = "/springmvc/old-controller")
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }

}
