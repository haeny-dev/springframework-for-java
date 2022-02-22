# 객체 지향 프로그래밍(Object-Oriented Programming, OOP)

## 객체 지향 프로그래밍

&nbsp;&nbsp;&nbsp;
객체 지향 프로그래밍(Object-Oriented Programming, OOP) 은 컴퓨터 프로그래밍의 패러다임 중 하나이다. 객체 지향 프로그래밍은 컴퓨터 프로그램을 명령어의
목록으로 보는 시각에서 벗어나 여러 개의 독립된 단위, 즉 **"객체"** 들의 모임으로 파악하고자 하는 것이다. 각각의 객체는 **메시지**를 주고받고, 데이터를 처리할 수 있다.

&nbsp;&nbsp;&nbsp;
객체 지향 프로그래밍은 프로그램을 유연하고 변경이 쉽게 만들기 때문에 대규모 소프트웨어 개발에 많이 사용된다.
유연하고 변경이 용이하다는 말은 무슨 의미일까? 컴퓨터의 키보드와 마우스를 예로 들 수 있다. 
키보드나 마우스는 어떤 제품을 연결하더라도 아무런 작업 없이 사용할 수 있다. 
이와 같이 컴포넌트를 쉽고 유연하게 변경하면서 개발할 수 있는 방법을 말한다.

## 객체 지향 프로그래밍의 특징

### 추상화(Abstraction)

### 캡슐화(Encapsulation)

### 상속(Inheritance)

### 다형성(Polymorphism)
: 서로 다른 객체들이 같은 메시지를 받았을 때 각자의 방식으로 동작하는 것을 말한다.

**다형성의 실세계 비유**
  
객체 지향의 특징을 실세계와 1:1 로 정확하게 매칭할 순 없지만 이해하는데에 도움이 될 수 있다. 
다형성은 실세계를 **역할**과 **구현**으로 구분 짓는 것과 같다.

&nbsp;&nbsp;&nbsp;
예를 들어, 운전자와 자동차에서 역할과 구현으로 구분지어 보면 운전자 역할과 자동차 역할이 있고 자동차 역할의 구현으로 K3, 아반떼, 산타페가 있다고 가정하면,
운전자 역할이 자동차 역할의 엑셀을 밟으면 K3, 아반떼, 산타페는 각자의 방식으로 동작할 것이다. 
또한 운전자는 자동차 엑셀이 각 자동차 내부에서 어떻게 동작하는지에 대해서는 모르더라도 어떤 동작을 할 것이라는 것만 알고 있으면 사용할 수 있다. 

&nbsp;&nbsp;&nbsp;
뮤지컬 공연을 예로 들 수 있다. 뮤지컬에는 여러 역할들이 존재하는데 각 역할에는 한 명의 배우만 캐스팅 되는 것이 아니라 여러 배우들이 캐스팅 된 것을 볼 수 있다.
해당 역할의 대사나 행동, 노래와 춤은 정해져있지만 그 역할을 소화해내는 배우에 따라 다른 연기를 볼 수 있다. 
그리고 각 역할들의 배우들은 다른 역할의 배우가 누구인지 모르더라도 서로 대사를 맞춰 연기를 펼쳐 낼 수 있다.

위의 실세계에서 볼 수 있는 특징들을 프로그래밍 세계로 옮겨보면 다음과 같다.

- *클라이언트는 대상의 역할(인터페이스)만 알면 된다.*
- *클라이언트는 구현 대상의 내부 구조를 몰라도 된다.*
- *클라이언트는 구현 대상의 내부 구조가 변경되어도 영향을 받지 않는다.*
- *클라이언트는 구현 대상 자체를 변경해도 영향을 받지 않는다.*

**자바 언어의 다형성**

자바 언어에서 역할과 구현으로 구분해보면 **역할**은 인터페이스, 부모 클래스로 볼 수 있고 **구현**은 인터페이스를 구현한 클래스나 부모 클래스를 상속받은 자식 클래스이다.

&nbsp;&nbsp;&nbsp;
**오버라이딩**을 떠올려보면 인터페이스의 메서드를 실행하였을 때, 인터페이스를 구현한 클래스의 오버라이딩 된 메서드가 실행된다. 물론 상속 관계의 객체에서도 마찬가지로 동작할 수 있다.

```java
/**
 * MemberRepository 역할(인터페이스)에 MemoryMemberRepository 구현(클래스) 사용
 */
public class MemberService {
    private MemberRepository memberRepository = new MemoryMemberRepository();
}
```
```java
/**
 *  MemberRepository 역할(인터페이스)에 JdbcMemberRepository 구현(클래스) 사용 
 */
public class MemberService {
//    private MemberRepository memberRepository = new MemoryMemberRepository();
    private MemberRepository memberRepository = new JdbcMemberRepository();
}
```

**다형성의 본질**

- 인터페이스를 구현한 객체 인스턴스를 실행 시점에 유연하게 변경할 수 있다.
- 다형성의 본질을 이해하려면 협력이라는 객체사이의 관계에서 시작해야 한다.
- 클라이언트를 변경하지 않고, 서버의 구현 기능을 유연하게 변경할 수 있다.

## 좋은 객체 지향 설계의 5가지 원칙(SOLID)

### SOLID
클린코드로 유명한 로버트 마틴이 좋은 객체 지향 설계의 5가지 원칙을 정리
- SRP(Single Responsibility Principle): 단일 책임 원칙
- OCP(Open-Closed Principle): 개방-폐쇄 원칙
- LSP(Liskov Substitution Principle): 리스코프 치환 원칙
- ISP(Interface Segregation Principle): 인터페이스 분리 원칙
- DIP(Dependency Inversion Principle): 의존관계 역전 원칙

#### 단일 책임 원칙 (Single Responsibility Principle, SRP)
- 한 클래스는 하나의 책임만 가져야 한다.
- 하나의 책임이라는 것은 모호하다. 클 수도 있고, 작을 수도 있는데 문맥과 상황에 따라 다르다.
- **중요한 기준은 변경이다.** 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것

#### 개방-폐쇄 원칙 (Open-Closed Principle, OCP)
- 소프트웨어 요소는 **확장에는 열려있으나 변경에는 닫혀** 있어야 한다.
- 객체 지향 프로그래밍의 특성인 다형성을 활용해보면 인터페이스를 구현한 새로운 구현체에 새로운 기능을 구현함으로써 확장할 수 있다.
  
    ```java
    /**
     * MemberRepository 역할(인터페이스)에 MemoryMemberRepository 구현(클래스) 사용
     */
    public class MemberService {
        private MemberRepository memberRepository = new MemoryMemberRepository();
    }
    ```
    ```java
    /**
     *  MemberRepository 역할(인터페이스)에 JdbcMemberRepository 구현(클래스) 사용 
     */
    public class MemberService {
        private MemberRepository memberRepository = new JdbcMemberRepository();
    }
    ```
- 하지만 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다.
- 다형성을 활용하였지만, OCP 원칙을 지킬 수 없다.

#### 리스코프 치환 원칙 (Liskov Substitution Principle, LSP)
- 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
- 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것, 다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체를 믿고 사용하려면 이 원칙이 필요하다.
- 단순히 컴파일에 성공하는 것을 넘어서는 내용이다.
- 예를 들어 자동차 인터페이스의 엑셀은 앞으로 가라는 기능인데, 뒤로 가도록 구현하면 LSP 를 위반한 것이다. 가능 방식이야 어떠하든 앞으로 가야한다.

#### 인터페이스 분리 원칙 (Interface Segregation Principle, ISP)
- 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
- 자동차 인터페이스 → 운전 인터페이스, 정비 인터페이스로 분리하면,
- 사용자 클라이언트 → 운전자 클라이언트, 정비사 클라이언트로 분리할 수 있다.
- 분리하면 정비 인터페이스 자체가 변해도 운전자 클라이언트에 영향을 주지 않는다.
- 인터페이스가 명확해지고, 대체 가능성이 높아진다.

#### 의존관계 역전 원칙 (Dependency Inversion Principle, DIP)
- 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다."
- 구현 클래스에 의존하지 말고, 인터페이스에 의존하라는 의미
- 클라이언트가 인터페이스에 의존해야 유연하게 구현체를 변경할 수 있다.

    ```java
    /**
     * < DIP 위반 > 
     * MemberService는 MemberRepository라는 인터페이스에 의존하지만, 
     * 실질적으로는 JdbcMemberRepository라는 구현 클래스에 의존하고 있다.
     */
    public class MemberService {
        private MemberRepository memberRepository = new JdbcMemberRepository();
    }
    ```
  
좋은 객체 지향 설계의 5가지 원칙인 SOLID 의 곳곳에서 다형성이 적용되는 것들을 볼 수 있다. 
그만큼 객체 지향 프로그래밍의 핵심은 다형성이라고 볼 수 있다. 
하지만 다형성 만으로는 구현 객체를 변경할 때 클라이언트 코드가 변경 되는 등 OCP, DIP를 지킬 수 없다.





  

  
  
  



