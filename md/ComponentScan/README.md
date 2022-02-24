# 컴포넌트 스캔

- 컴포넌트 스캔을 사용하려면 먼저 `@ComponentScan` 을 설정 정보 클래스에 붙여주면 된다. 


- `@ComponentScan` 은 `@Component` 가 붙은 모든 클래스를 스프링 빈으로 등록한다.


- `@Configuration` 소스코드를 열어보면 `@Component` 가 있어서 자동으로 빈으로 등록된다.


- 이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.


- 만약 빈 이름을 직접 지정하고 싶으면 `@Component("member")` 이런식으로 이름을 부여하면 된다.


- `basePackages` 옵션을 통해서 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다.


- `basePackage = {"hello.core", "hello.service"}` 이렇게 여러 시작 위치를 지정할 수도 있다.


- `basePackageClasses` 옵션을 사용하여 지정한 클래스를 탐색 시작 위치로 지정한다.


- 만약 지정하지 않으면 `@ComponentScan` 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.


- 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 `@SpringBootApplication` 를 해당 프로젝트 시작 루트 위치에 두는 것이 관례이다. 
  그리고 이 설정 안에 `@ComponentScan` 이 들어있다.
  

- 컴포넌트 스캔은 `@Component` 뿐만 아니라 `@Controller`, `@Service`, `@Repository`, `@Configuration` 과 같은 애노테이션도 대상에 포함한다.


- 사실 해당 애노테이션 코드를 열어보면 `@Component` 를 포함하고 있다.


- `@Controller`: 스프링 MVC 컨트롤러로 인식


- `@Repository`: 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.


- `@Service`: 특별한 처리를 하진 않지만, 개발자들이 비즈니스 계층임을 인식하는데 도움이 된다.


- `useDefaultFilters` 옵션은 기본으로 켜져 있는데, 이 옵션을 끄면 기본 스캔 대상들이 제외된다.
그냥 이런 옵션이 있구나 정도만 알고 넘어가자.
  

- `includeFilters` 옵션은 컴포넌트 스캔 대상을 추가로 지정한다.


- `excludeFilters` 옵션은 컴포넌트 스캔에서 제외할 대상을 지정한다.


- `FilterType` 은 5가지 옵션이 있다.
    - ANNOTATION: 기본 값이며, 애노테이션을 인식해서 동작한다.
    - ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
    - ASPECTJ: AspectJ 패턴 사용
    - REGEX: 정규표현식 사용
    - CUSTOM: `TypeFilter` 라는 인터페이스를 구현해서 처리
    

- 컴포넌트 스캔에 의해 자동으로 스프링 빈이 등록되는데, 그 이름이 같은 경우 `ConflictingBeanDefinitionException` 예외를 발생시킨다.


- 수동 빈 등록과 자동 빈 등록에서 빈 이름이 충돌되는 경우 수동 빈 등록이 우선권을 가진다.


- 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌나면 오류가 발생하도록 기본 값을 변경하였다.
