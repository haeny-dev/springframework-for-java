package jpql;

import jpql.domain.*;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member memberA = new Member();
            memberA.setUsername("teamA");
            memberA.setAge(10);
            memberA.setTeam(teamA);
            em.persist(memberA);

            em.flush();
            em.clear();

            em.createQuery("select" +
                    " case when m.age <= 10 then '학생요금'" +
                    "      when m.age >= 60 then '경로요금'" +
                    "      else '일반요금'" +
                    " end" +
                    " from Member m").getResultList();

            em.createQuery("select coalesce(m.username, '이름 없는 회원') from Member m")
                    .getResultList();

            em.createQuery("select nullif(m.username, '관리자') from Member m")
                    .getResultList();


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }

    private static void enumStatement(EntityManager em) {
        Member memberA = new Member();
        memberA.setUsername("member");
        memberA.setAge(10);
        memberA.setMemberType(MemberType.ADMIN);
        em.persist(memberA);
        em.flush();
        em.clear();

        em.createQuery("select m from Member m where m.memberType = jpql.domain.MemberType.ADMIN", Member.class)
                .getResultList();
    }

    private static void subquery(EntityManager em) {
        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setUsername("member" + (i + 1));
            member.setAge(i + 1);
            em.persist(member);

            if (i % 2 == 0) {
                Order order = new Order();
                order.setMember(member);
                em.persist(order);
            }
        }

        em.createQuery("select m from Member m where m.age > (select avg(m2.age) from Member m2)", Member.class)
                .getResultList();

        em.createQuery("select m from Member m where (select count(o) from Order o where m = o.member) > 0", Member.class)
                .getResultList();
    }

    private static void join(EntityManager em) {
        Team teamA = new Team();
        teamA.setName("teamA");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setName("teamB");
        em.persist(teamB);

        Member memberA = new Member();
        memberA.setUsername("teamA");
        memberA.setAge(10);
        memberA.setTeam(teamA);
        em.persist(memberA);

        em.flush();
        em.clear();

        // 내부 조인
        em.createQuery("select m from Member m inner join m.team t", Member.class)
                .getResultList();

        // 외부 조인
        em.createQuery("select m from Member m left outer join m.team t", Member.class)
                .getResultList();

        // 세타 조인
        em.createQuery("select count(m) from Member m, Team t where m.username = t.name")
                .getSingleResult();

        // On절 활용 - 조인 대상 필터링
        em.createQuery("select m from Member m left join m.team t on t.name = 'teamA'", Member.class)
                .getResultList();

        // On절 활용 - 연관관계 없는 엔티티 외부 조인
        em.createQuery("select m from Member m left join Team t on m.username = t.name", Member.class);
    }

    private static void paging(EntityManager em) {
        for (int i = 0; i < 100; i++) {
            Member member = new Member();
            member.setUsername("member" + (i + 1));
            member.setAge(i + 1);
            em.persist(member);
        }
        em.flush();
        em.clear();

        List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList();

        result.forEach(System.out::println);
    }

    private static void projection(EntityManager em) {
        Member member = new Member();
        member.setUsername("member");
        member.setAge(10);
        em.persist(member);
        em.flush();
        em.clear();

        // 엔티티 프로젝션
        em.createQuery("select m from Member m", Member.class);
        em.createQuery("select m.team from Member m", Team.class);

        // 임베디드 타입 프로젝션
        em.createQuery("select o.address from Order o", Address.class);
        // 스칼라 타입
        em.createQuery("select distinct m.username, m.age from Member m");
        // new 명령어로 조회
        em.createQuery("select new jpql.domain.MemberDTO(m.username, m.age)" +
                " from Member m", MemberDTO.class);
    }

    private static void jpqlBasicApi(EntityManager em) {
        Member member = new Member();
        member.setUsername("member");
        member.setAge(10);
        em.persist(member);

        // 반환 타입이 명확할 때 사용
        TypedQuery<Member> typedQuery = em.createQuery("select m from Member m", Member.class);

        // 반환 타입이 명확하지 않을 때 사용
        Query query = em.createQuery("select m.username from Member m");

        // 결과 조회
        // 결과가 하나 이상일 때
        List<Member> members = typedQuery.getResultList();

        // 결과가 정확히 하나
        // - 결과가 없으면 -> NoResultException
        // - 둘 이상이면 -> NonUniqueResultException
        String username = (String) query.getSingleResult();

        em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", member.getUsername());
    }
}
