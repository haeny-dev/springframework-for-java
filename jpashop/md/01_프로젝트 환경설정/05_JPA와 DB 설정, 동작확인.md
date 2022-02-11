### JPA와 DB설정, 동작확인

*yaml 파일 설정*
```yaml
spring:
  datasource:
    url: jdbc:h2:tcp://localhost/~/jpashop
    username: sa
    password:
    driver-class-name: org.h2.Driver

  jpa:
    hibernate:
      ddl-auto: create    # 애플리케이션 실행 시점에 테이블을 drop하고, 다시 생성한다.
    properties:
      hibernate:
#        show_sql: true   # System.out 에 하이버네이트 실행 SQL을 남기므로 지양해야한다.
        format_sql: true

logging:
  level:
    org.hibernate.SQL: debug  # logger를 통해 하이버네이트 SQL을 남긴다.
    org.hibernate.type: trace # SQL 실행 파라미터를 로그로 남긴다.
```

*SQL 쿼리 파라미터 로그 남기기*
- yaml 파일에 `org.hibernate.type: trace` 추가
- `com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0` 외부 라이브러리 추가
- 쿼리 파라미터를 로그로 남기는 외부라이브러리는 시스템 자원을 사용하기 때문에 개발 단계에서는 편하게 사용해도 된다.
- 하지만 운영시스템에 적용할 때는 꼭 성능테스트 등의 고려가 필요하다.

*jar 동작확인*
- `./gradlew clean build`
- `cd build`
- `cd libs`
- `java -jar jpashop-0.0.1-SNAPSHOT.jar`

*참고*
- [spring-boot-data-source-decorator](https://github.com/gavlyukovskiy/spring-boot-data-source-decorator)