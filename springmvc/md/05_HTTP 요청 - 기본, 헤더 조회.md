### HTTP 요청 - 기본, 헤더 조회

*파라미터*
- `HttpServletRequest`
- `HttpServletResponse`
- `HttpMethod` : HTTP 메서드를 조회한다. `org.springframework.http.HttpMethod`
- `Locale` : Locale 정보를 조회한다.
- `@RequestHeader MultiValueMap<String, String> headerMap`
    - 모든 HTTP 헤더를 MultiValueMap 형식으로 조회한다.
- `@RequestHeader("host") String host`
    - 특정 HTTP 헤더를 조회한다.
    - 속성
        - 필수 값 여부: required
        - 기본 값 속성: defaultValue
- `@CookieValue(value = "myCookie", required = false) String cookie`
    - 특정 쿠키를 조회한다.
    - 속성
        - 필수 값 여부: required
        - 기본 값: defaultValue

*MultiValueMap*
- MAP과 유사한데, 하나의 키에 여러 값을 받을 수 있다.
- HTTP header, HTTP 쿼리 파라미터와 같이 하나의 키에 여러 값을 받을 때 사용한다.
    - keyA=value1&keyA=value2

*참고*
- [사용 가능한 파라미터 목록](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-nts)
- [사용 가능한 응답 값 목록](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-return-types)