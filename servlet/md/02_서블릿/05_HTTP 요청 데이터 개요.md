### HTTP 요청 데이터 개요

*HTTP 요청 메시지를 통해 클라이언트에서 서버로 데이터를 전달하는 방법*
- GET
    - 쿼리 파라미터
    - /url?username=hello&age=20
    - 메시지 바디 없이, Url 의 쿼리 파라미터에 데이터를 포함해서 전달
    - 검색, 필터, 페이징 등에서 많이 사용
    
- POST
    - HTML Form
    - Content-Type: application/x-www-form-urlencoded
    - 메시지 바디에 쿼리 파라미터 형식으로 전달
    - 회원 가입, 상품 주문, HTML Form 사용
    
- HTTP message body 에 데이터를 직접 담아 요청
    - HTTP API 에서 주로 사용
    - JSON, XML, TEXT
    - 데이터 형식은 주로 JSON 을 사용
    - POST, PUT, PATCH 등 사용가능
    
