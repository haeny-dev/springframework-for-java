package inflearn.thejava.annotationprocessor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    void getterSetter() {
        Member member = new Member();
        member.setName("haeny");

        assertThat(member.getName()).isEqualTo("haeny");
    }

}