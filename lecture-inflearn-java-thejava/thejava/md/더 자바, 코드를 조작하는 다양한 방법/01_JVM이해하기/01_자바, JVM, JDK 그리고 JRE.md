# 더 자바, 코드를 조작하는 다양한 방법
## 01. JVM 이해하기
### 1) 자바, JVM, JDK 그리고 JRE

*JVM(Java Virtual Machine)*

- 자바 가상 머신으로 자바 바이트 코드(.class 파일)를 OS에 특화된 코드로 변환(인터프리터와 JIT컴파일러)하여 실행한다.
    - 해당 OS에 특화된 바이트코드로 변환해야 하기 때문에 특정 플랫폼에 종속적이라고 할 수 있다.
- 바이트 코드를 실행하는 표준(JVM 자체는 표준)이자 구현체(특정 밴더가 구현한 JVM)이다.
    - JVM 밴더: 오라클, 아마존, Azul 등등

*JRE(Java Runtime Environment)*

- 자바 애플리케이션을 실행할 수 있도록 구성된 환경
- JVM 혼자 주어지지 않으며, 최소한의 배포단위가 JRE 이다.
- JVM 과 핵심 라이브러리 및 자바 런타임 환경에서 사용하는 프로퍼티 세팅이나 리소스 파일을 가지고 있다.
- 개발 관련 도구는 포함하지 않는다. 즉, JRE 에는 컴파일러가 없다.

*JDK(Java Development Kit)*

- JRE + 개발에 필요한 툴
- 소스 코드를 작성할 때 사용하는 자바 언어는 플랫폼에 독립적이다.
- 오라클은 자바 11부터는 JDK 만 제공하며 JRE 를 따로 제공하지 않는다.

*Java*
- 프로그래밍 언어
- JDK 에 들어있는 자바 컴파일러(javac)를 사용하여 바이트코드(.class 파일)로 컴파일 할 수 있다.
- 자바 유료화? 라는 말이있는데, 정확히는 오라클에서 만든 Oracle JDK 11버전부터 상용으로 사용할 때 유료다.


*JVM 언어*
- JVM 기반으로 동작하는 프로그래밍 언어
- 클로저, 그루비, JRuby, Jython, Kotlin, Scala, ...

*참고*
- [JVM 스펙](https://docs.oracle.com/javase/specs/jvms/se11/html/)
- [JIT컴파일러](https://aboullaite.me/understanding-jit-compiler-just-in-time-compiler/)
- [JDK, JRE 그리고 JVM](https://howtodoinjava.com/java/basics/jdk-jre-jvm/)
- [유료에 대한 참고 글](https://medium.com/@javachampions/java-is-still-free-c02aef8c9e04)





