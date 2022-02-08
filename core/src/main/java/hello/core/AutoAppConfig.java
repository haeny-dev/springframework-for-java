package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /**
         * default
         * - @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다.
         * - 권장하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 것이다.
         * - 스프링부트도 이 방법을 기본으로 제공한다. (@SpringBootApplication)
         */
//        basePackages = "hello.core",
//        basePackageClasses = AutoAppConfig.class,
        // 기존의 AppConfig.class를 제외하기 위해서 설정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

public class AutoAppConfig {
/*
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
*/

}
