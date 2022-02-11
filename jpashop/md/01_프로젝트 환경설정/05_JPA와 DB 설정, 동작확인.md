- `jdbc:h2:tcp://localhost/~/jpashop;MVCC=TRUE` 에서 MVCC=TRUE 넣어주는게 권장 ---- XXX

- System.out.println 에 출력 -> 그러므로 권장되지 않는다.
```yaml
  jpa:
    hibernate:
      ddl-auto: create
    properties:
      hibernate:
        show_sql: true
```

- log 로 출력
```yaml
logging:
  level: 
    org.hibernate.SQL: debug
```

- jar 동작확인
  - `./gradlew clean build`
  - `cd build`
  - `cd libs`
  - `java -jar jpashop-0.0.1-SNAPSHOT.jar`

- ?키워드
    - junit4 vs junit5