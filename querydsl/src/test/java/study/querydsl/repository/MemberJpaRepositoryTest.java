package study.querydsl.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Member;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Test
    void basicTest() {
        // given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        // when
        // then
        memberJpaRepository.findById(member.getId())
                .ifPresent(value -> assertThat(value).isEqualTo(member));

        assertThat(memberJpaRepository.findAll()).containsExactly(member);
        assertThat(memberJpaRepository.findByUsername("member1")).containsExactly(member);
    }

    @Test
    void basicQuerydslTest() {
        // given
        Member member = new Member("member1", 10);
        memberJpaRepository.save(member);

        // when
        // then
        memberJpaRepository.findById(member.getId())
                .ifPresent(value -> assertThat(value).isEqualTo(member));

        assertThat(memberJpaRepository.findAll_Querydsl()).containsExactly(member);
        assertThat(memberJpaRepository.findByUsername_Querydsl("member1")).containsExactly(member);
    }

}