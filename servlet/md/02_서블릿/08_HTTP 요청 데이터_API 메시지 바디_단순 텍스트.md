### HTTP 요청 데이터 - API 메시지 바디 - 단순 텍스트

*HTTP message body 에 단순 텍스트 요청*
- `Content-Type: text/plain`
- HTTP 메시지 바디의 데이터를 InputStream 을 사용해서 직접 읽을 수 있다.
- InputStream 은 Byte 코드를 반환한다.
- 우리가 읽을 수 있는 문자로 변환하기 위해 Charset을 지정해주어야 한다.
