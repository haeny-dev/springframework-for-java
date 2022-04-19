package study.datajpa.repository;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.*;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    EntityManager em;


    @Test
    void testMember() {
        // given
        Member member = new Member("memberA");

        // when
        Member saveMember = memberRepository.save(member);
        Member findMember = memberRepository.findById(saveMember.getId()).orElse(null);

        // then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).orElse(null);
        Member findMember2 = memberRepository.findById(member2.getId()).orElse(null);

        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
        assertThat(memberRepository.count()).isEqualTo(2);

        memberRepository.delete(member1);
        memberRepository.delete(member2);
        assertThat(memberRepository.count()).isEqualTo(0);
    }

    @Test
    void findByUsernameAndAgeGreaterThan() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void testNamedQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);

        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    void testQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("AAA", 10);
        Member findMember = result.get(0);

        assertThat(findMember).isEqualTo(m1);
    }

    @Test
    void findUsernameList() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();
        assertThat(usernameList).contains(m1.getUsername(), m2.getUsername());
    }

    @Test
    void findMemberDto() {
        Team teamA = new Team("teamA");
        teamRepository.save(teamA);

        Member m1 = new Member("AAA", 10);
        memberRepository.save(m1);

        m1.changeTeam(teamA);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        MemberDto findMemberDto = memberDto.get(0);

        assertThat(findMemberDto.getId()).isEqualTo(m1.getId());
        assertThat(findMemberDto.getUsername()).isEqualTo(m1.getUsername());
        assertThat(findMemberDto.getTeamName()).isEqualTo(m1.getTeam().getName());
    }

    @Test
    void findByNames() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> members = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        assertThat(members).contains(m1, m2);
    }

    @Test
    void returnType() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> findMemberList = memberRepository.findListByUsername("AAA");
        assertThat(findMemberList.size()).isEqualTo(2);
        assertThat(findMemberList).contains(m1, m2);

        Assertions.assertThrows(IncorrectResultSizeDataAccessException.class,
                () -> memberRepository.findMemberByUsername("AAA"));

        Assertions.assertThrows(IncorrectResultSizeDataAccessException.class,
                () -> memberRepository.findOptionalMemberByUsername("AAA"));
    }

    @Test
    void paging() {
        // given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        // when
        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3,
                Sort.by(Sort.Direction.DESC, "username"));

        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        // DTO로 변환
        Page<MemberDto> dtoPage = page.map(m -> new MemberDto(m.getId(), m.getUsername(), null));

        // then
        assertThat(page.getSize()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getTotalPages()).isEqualTo(2);
        assertThat(page.getNumber()).isEqualTo(0);
        assertThat(page.isFirst()).isTrue();
        assertThat(page.hasNext()).isTrue();
    }

    @Test
    void slice() {
        // given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        // when
        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3,
                Sort.by(Sort.Direction.DESC, "username"));

        Slice<Member> slice = memberRepository.findSliceByAge(age, pageRequest);

        // then
        assertThat(slice.getSize()).isEqualTo(3);
//        assertThat(slice.getTotalElements()).isEqualTo(5);
//        assertThat(slice.getTotalPages()).isEqualTo(2);
        assertThat(slice.getNumber()).isEqualTo(0);
        assertThat(slice.isFirst()).isTrue();
        assertThat(slice.hasNext()).isTrue();
    }

    @Test
    void bulkUpdate() {
        // given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));

        // when
        int resultCount = memberRepository.bulkAgePlus(20);
        // then
        assertThat(resultCount).isEqualTo(3);

        Member member5 = memberRepository.findByUsername("member5").get(0);
//        assertThat(member5.getAge()).isEqualTo(40);
//
//        em.flush();
//        em.clear();
//
//        member5 = memberRepository.findByUsername("member5").get(0);
        assertThat(member5.getAge()).isEqualTo(41);
    }

    @Test
    void findMemberLazy() {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        PersistenceUnitUtil util = em.getEntityManagerFactory().getPersistenceUnitUtil();

        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            System.out.println("member.getUsername() = " + member.getUsername());
            System.out.println("member.getTeam().getClass() = " + member.getTeam().getClass());
            System.out.println("Hibernate.isInitialized(member.getTeam()) = " + Hibernate.isInitialized(member.getTeam()));
            System.out.println("util.isLoaded(member.getTeam()) = " + util.isLoaded(member.getTeam()));
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
        }
    }

    @Test
    void findMemberFetchJoin() {
        // given
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");

        teamRepository.save(teamA);
        teamRepository.save(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);

        memberRepository.save(member1);
        memberRepository.save(member2);

        em.flush();
        em.clear();

        List<Member> members = memberRepository.findMemberFetchJoin();
        for (Member member : members) {
            System.out.println("member = " + member);
        }
    }

    @Test
    void queryHint() {
        // given
        memberRepository.save(new Member("member1", 10));
        em.flush();
        em.clear();

        // when
//        Member findMember = memberRepository.findByUsername("member1").get(0);
//        findMember.setUsername("member2");
//        em.flush(); // update Query 실행

        Member readOnlyMember = memberRepository.findReadOnlyByUsername("member1");
        readOnlyMember.setUsername("member3");
        em.flush(); // update Query 실행 X
    }

    @Test
    void lock() {
        // given
        memberRepository.save(new Member("member1", 10));
        em.flush();
        em.clear();

        memberRepository.findLockByUsername("member1");
    }

    @Test
    void callCustom() {
        List<Member> result = memberRepository.findMemberCustom();
    }

    @Test
    void specBasic() {
        // given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        // when
        Specification<Member> spec = MemberSpec.username("m1").and(MemberSpec.teamName("teamA"));
        List<Member> result = memberRepository.findAll(spec);

        // then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void projections() {
        // given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        // when
//        List<UsernameOnly> result = memberRepository.findProjectionsByUsername("m1");
//        List<UsernameOnlyDto> result = memberRepository.findProjectionsDtoByUsername("m1");
//        List<UsernameOnly> result = memberRepository.findDynamicProjectionByUsername("m1", UsernameOnly.class);
        List<NestedClosedProjection> result = memberRepository.findDynamicProjectionByUsername("m1", NestedClosedProjection.class);

        // then
        assertThat(result.get(0).getUsername()).isEqualTo("m1");
    }

    @Test
    void nativeQuery() {
        // given
        Team teamA = new Team("teamA");
        em.persist(teamA);

        Member m1 = new Member("m1", 0, teamA);
        Member m2 = new Member("m2", 0, teamA);
        em.persist(m1);
        em.persist(m2);

        em.flush();
        em.clear();

        // when
//        Member result = memberRepository.findByNativeQuery("m1");

        // then
//        assertThat(result.getUsername()).isEqualTo("m1");

        Page<MemberProjection> result = memberRepository.findByNativeProjection(PageRequest.of(0, 10));
        for (MemberProjection memberProjection : result.getContent()) {
            System.out.println("memberProjection.getUsername() = " + memberProjection.getUsername());
            System.out.println("memberProjection.getTeamName() = " + memberProjection.getTeamName());
        }
    }
}