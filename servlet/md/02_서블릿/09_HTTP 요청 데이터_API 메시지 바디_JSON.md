### HTTP 요청 데이터 - API 메시지 바디 - JSON

*HTTP message body 에 json 데이터 요청*
- `Content-Type: application/json`

- JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면 Jackson, Gson 같은 JSON 변환 라이브러리를 추가해서 사용해야 한다.
- SpringBoot 는 기본으로 Jackson 라이브러리를 함께 제공한다.

