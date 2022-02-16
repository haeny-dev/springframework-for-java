### Hello 서블릿

*@ServletComponentScan*
- 서블을 찾아서 자동으로 등록해주는 애노테이션

*@WebServlet*
- 서블릿 애노테이션
    - name: 서블릿 이름
    - urlPatterns: url 매핑
    
*HTTP 요청 메시지 로그로 확인하기*
- 설정 파일에 해당 설정을 추가
- `logging.lovel.org.apache.coyote.http11=debug`
- 운영서버에 남기면 성능저하가 발생할 수 있으니, 개발 단계에서만 적용하자.