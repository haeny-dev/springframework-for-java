### HttpServletRequest 개요

*HttpServletRequest 역할*
- HTTP 요청 메시지
    - 서블릿은 개발자가 HTTP 요청 메시지를 편리하게 사용할 수 있도록 개발자 대신에 HTTP 요청 메시지를 파싱한다.
    - 그리고 그 결과를 `HttpServletRequest` 객체에 담아서 제공한다.
    
- 임시 저장소 기능
    - 해당 HTTP 요청이 시작부터 끝날 때 까지 유지되는 임시 저장소 기능
        - 저장: request.setAttribute(name, value)
        - 조회: request.getAttribute(name)
    
- 세션 관리 기능
    - request.getSession()
    
- `HttpServletRequest`, `HttpServletResponse` 를 사용할 때 가장 중요한 점은 
  이 객체들이 HTTP 요청 메시지, HTTP 응답 메시지를 편리하게 사용하도록 도와주는 객체라는 점이다.


