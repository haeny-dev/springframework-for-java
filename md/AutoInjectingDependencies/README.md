# 의존관계 자동 주입

### 의존관계 주입은 크게 4가지 방법이 있다.

- 생성자 주입

    ```java
    /**
     * - 생성자를 통해서 의존관계를 주입 받는 방법
     * - 굳이 new 를 통해 인스턴스를 만들지 않는다면 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다.
     * - 불변, 필수 의존관계에 사용
     * - 생성자 주입은 생성자가 딱 1개만 있으면 @Autowired 생략가능
     */
    @Component
    public class OrderServiceImpl implements OrderService {
        
        private final MemberRepository memberRepository;
        
        @Autowired
        public OrderServiceImpl(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
    }
    ```

- 수정자 주입(Setter 주입)

    ```java
    /**
     * - setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해 의존관계를 주입 하는 방법
     * - 선택, 변경 가능성이 있는 의존관계에 사용
     * - 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
     */
    @Component
    public class OrderServiceImpl implements OrderService {
        private MemberRepository memberRepository;
        
        @Autowired
        public void setMemberRepository(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }
    }
    ```



- 필드 주입

  ```java
  /**
   * - 필드에 바로 주입하는 방법이다.
   * - 코드가 간결해서 좋아보이지만, 외부에서 변경이 불가능해서 테스트 하기 힘들다는 단점이 있다.
   * - DI 컨테이너가 없으면 아무것도 할 수 없다.
   * - 되도록 사용하지 말 것. 테스트 코드에서 테스트를 위해서 가져오는 정도로만 사용할 것
   */
  @Component
  public class OrderServiceImpl implements OrderService {
      @Autowired
      private MemberRepository memberRepository;
  }
  ```

- 일반 메서드 주입

  ```java
  /**
   * - 일반 메서드를 통해서 주입 받을 수 있다
   * - 한번에 여러 필드를 주입 받을 수 있다.
   * - 일반적으로 잘 사용하지 않는다.
   */
  @Component
  public class OrderServiceImpl implements OrderService {
  
      private MemberRepository memberRepository;
  
      @Autowired
      public void init(MemberRepository memberRepository) {
          this.memberRepository = memberRepository;
      }
  }
  ```

- 의존관계 자동 주입은 스프링 컨테이너가 관리하는 스프링 빈 이어야 동작한다. 
  스프링 빈이 아닌 `@Component` 가 없는 클래스에서 `@Autowired` 를 적용해도 아무 기능도 동작하지 않는다.


- 순수한 자바 테스트 코드에는 `@Autowired` 가 동작하지 않는다. 
  `@SpringBootTest` 처럼 스프링 컨테이너를 테스트에 통합한 경우에만 가능하다.


- `@Autowired` 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다. 
  주입할 대상이 없어도 동작하게 하려면 `@Autowired(required = false)` 로 지정하면 된다.


- `@Autowired` 적용된 수정자 주입 또는 메서드 주입 방식에서 매개변수에 `@Nullable` 을 사용하면 자동 주입할 대상이 없으면 `null` 이 입력된다.


- `@Autowired(require = false)` 적용된 주입 방식에서 매개변수에 `Optional<>` 을 사용하면 자동 주입할 대상이 없으면 `Optional.empty` 가 입력된다.


### 스프링을 포함한 DI 프레임워크 대부분이 생성자 주입을 권장한다.

- 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다.
  오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.

  
- 수정자 주입을 사용하면 setter를 public 으로 열어두어야 하는데, 
  setter 메서드 하나로 쉽게 변경용이 하도록 하는 설계는 좋은 설계가 아니다.

  
- 생성자 주입은 객체를 생성할 때 1번만 호출되므로 이후에 호출되는 일이 없다. 
  따라서 불변하게 설계할 수 있다.

  
- 필드 주입을 사용하면 테스트할 때 구현체를 변경할 수 없다.


- 수정자 주입을 사용할 경우 스프링 컨테이너 없이 순수 자바 코드로 단위 테스트 하는 경우에 수정자를 통해서 주입해주지 않아도 컴파일 에러가 발생하지 않고, 그로 인해 런타임 에러를 발생시킬 수 있다.


- 생성자 주입의 경우에만 필드에 `final` 키워드를 사용할 수 있다. 
  이 경우 생성자에 주입 데이터를 누락 했을 때 컴파일 오류가 발생한다.


- 생성자 주입 방식을 선택하는 이유는 어려가지가 있지만, 프레임워크에 의존하지 않고 순수한 자바 언어의 특징을 잘 살리는 방법이기도 하다.


- 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다.


### 같은 타입의 빈이 2개 이상일 때

- `@Autowired` 의 기본 조회 전략은 타입이 같은 빈을 찾아서 주입힌다.


- `@Autowired` 는 타입 매칭을 시도하고, 이때 여러 빈이 있으면 필드 이름, 파라미터 이름으로 빈 이름을 추가 매칭한다.


- `@Qualifier` 는 추가 구분자를 붙여주는 방법이다. 주입시 추가적인 방법을 제공하는 것이지 빈 이름을 변경하는 것은 아니다.


- `@Qualifier` 는 주입할 때 `@Qualifier("memberRepository")` 를 못 찾으면 memberRepository 라는 이름의 스프링 빈을 추가로 찾는다.


- 그럼에도 불구하고 매칭되는 빈이 없으면 `@Qualifier` 는 `NoSuchBeanDefinitionException` 예외를 발생시킨다.


- `@Primary` 는 우선순위를 정하는 방법이다. 여러 빈이 매칭되면 `@Primary` 가 우선권을 가진다.


- `@Primary` 와 `@Qualifier` 활용  
  : 애플리케이션 내부에 코드에서 주로 사용하는 메인 데이터베이스 커넥션을 획득하는 빈이 있고, 코드에서 특별한 기능으로 가끔 사용하는 서브 데이터베이스 커넥션을 획득하는 스프링 빈이 있다고 가정해보자.
  메인 데이터베이스 커넥션을 획득하는 스프링 빈은 `@Primary` 를 적용해서 주로 사용하는 곳에서 편리하게 조회하고,
  서브 데이터베이스 커넥션 빈을 획득할 때는 `@Qualifier` 를 지정해서 명시적으로 획득하는 방식으로 사용하면 코드를 깔끔하게 유지할 수 있다.
  

- 조회한 빈이 모두 필요할 때는 List, Map 형태로 주입받을 수 있다.


- Map 형태로 주입받을 때 Map 의 키에 빈의 이름을 넣어주고, 그 값으로 빈을 담아준다.


