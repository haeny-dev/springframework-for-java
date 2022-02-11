### View 환경 설정

*Thymeleaf 템플릿엔진*
- 요즘에는 가급적으로 JSP보다 Thymeleaf를 사용하는 추세이다.
- 스프링에서 타임리프를 밀고 있고, 타임리프에서도 스프링 관련 기능을 제공하기도 한다.
- 요즘에는 어드민 페이지 같은곳에 사용하기도 한다.

*장단점*
- 장점은 마크업을 형태를 유지한다는 것이다.
  - 템플릿 파일을 웹 브라우저에서 바로 실행시킬 수 있음.
- 2.x.x 버전의 극단적인 단점은 태그를 열고 무조건 닫아주는 형태로 꼭 사용해야 된다는 것이다.
- 하지만, 3.x.x 버전에서 그러한 단점을 극복했다. 그리고 성능 이슈도 있었지만 많이 개선되었다고 한다.
- 메뉴얼을 참고하여 정확하게 사용법을 알고 사용해야 한다.

*스프링 부트 viewName 매핑*
- `resources:templates/` + {ViewName} + `.html`
  ```java
  @Controller
    public class HelloController {
        @GetMapping("hello")
        public String hello(Model model) {
            model.addAttribute("data", "hello!!");
            return "hello";
        }
  }
  ```

*톰캣을 띄워놓은 상태에서 html 수정 시 바로 반영될 수 있게 하는 방법*
- `org.springframework.boot:spring-boot-devtools` 추가
- Run > recompile 하면 서버를 다시 안올려도 반영이된다.
  

*참고*
- [thymeleaf 공식 사이트](https://www.thymeleaf.org)
- [스프링 공식 튜토리얼](https://spring.io/guides/gs/serving-web-content/)
- [스프링 부트 메뉴얼](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/html/boot-features-developing-web-applications.html#boot-features-spring-mvc-template-engines)




