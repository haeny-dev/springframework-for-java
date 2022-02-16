### MVC 패턴 - 한계

*포워드, viewPath 중복*
  
```java
String viewPath = "/WEB-INF/views/members.jsp";
RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
dispatcher.forward(request, response);
```
  
- View 로 이동하는 코드가 항상 중복 호출되어야 한다.
- 만약 jsp 가 아닌 thymeleaf 같은 다른 뷰로 변경한다면 전체 코드를 다 변경해야 한다.
    
*사용하지 않는 코드*
- `HttpServletResponse` 는 사용되지 않는 경우가 많다.
- 그리고 이러한 `HttpServletRequest`, `HttpServletResponse` 를 사용하는 코드는 테스트 케이스르 작성하기도 어렵다.
    
*공통 처리가 어렵다*
- 기능이 복잡해질수록 컨트롤러에서 공통으로 처리해야 하는 부분이 점점 더 많이 증가할 것이다.
- 단순히 공통 기능을 메서드로 뽑으면 될 것 같지만, 결과적으로 해당 메서드를 항상 호출해야 한다.
- 실수로 호출하지 않으면 문제가 발생할 것이다.
    

    