package hellojpa;

import hellojpa.domain.*;
import hellojpa.domain.item.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();    // 트랜잭션 시작

        try {


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void criteria(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        Root<Member> m = query.from(Member.class);

        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));
        List<Member> members = em.createQuery(cq).getResultList();
    }

    private static void jpql(EntityManager em) {
        List<Member> members = em.createQuery(
                "select m from Member m where m.username like '%kim%'", Member.class)
                .getResultList();

    }

    private static void valueType(EntityManager em) {
        Address address = new Address("seoul", "street", "14582");

        Member member1 = new Member();
        member1.setUsername("member1");
        member1.setHomeAddress(address);

        member1.getFavoriteFoods().add("치킨");
        member1.getFavoriteFoods().add("탕수육");
        member1.getFavoriteFoods().add("족발");

        AddressEntity old1 = new AddressEntity(new Address("old1", "street", "13234"));
        AddressEntity old2 = new AddressEntity(new Address("old2", "street", "13234"));
        member1.getAddressHistory().add(old1);
        member1.getAddressHistory().add(old2);
        em.persist(member1);
        em.flush();
        em.clear();

        Member findMember = em.find(Member.class, member1.getId());

        findMember.getFavoriteFoods().forEach(System.out::println);
        findMember.getAddressHistory().stream().map(AddressEntity::getAddress).forEach(a -> {
            System.out.println("address.getCity() = " + a.getCity());
            System.out.println("address.getStreet() = " + a.getStreet());
            System.out.println("address.getZipcode() = " + a.getZipcode());
        });

        // 치킨 -> 한식
        findMember.getFavoriteFoods().remove("치킨");
        findMember.getFavoriteFoods().add("한식");

        // 주소 히스토리 변경
        findMember.getAddressHistory().remove(old1);
        findMember.getAddressHistory().add(new AddressEntity(new Address("newCity1", "Street", "31523")));
    }

    private static void cascade(EntityManager em) {
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        parent.addChild(child1);
        parent.addChild(child2);

        em.persist(parent);

        em.flush();
        em.clear();

        Parent findParent = em.find(Parent.class, parent.getId());
        findParent.getChildList().remove(0);
    }

    private static void lazyloading(EntityManager em) {
        Team teamA = new Team();
        teamA.setName("teamA");
        em.persist(teamA);

        Team teamB = new Team();
        teamB.setName("teamB");
        em.persist(teamB);

        Member member1 = new Member();
        member1.setUsername("member1");
        member1.setTeam(teamA);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("member2");
        member2.setTeam(teamB);
        em.persist(member2);

        em.flush();
        em.clear();

//            Member findMember = em.find(Member.class, member.getId());
//            System.out.println("findMember.getTeam().getClass() = " + findMember.getTeam().getClass());
//            System.out.println("findMember.getTeam().getName() = " + findMember.getTeam().getName());

        // Team 의 FetchType.EAGER 일 때 N + 1 문제를 발생시킨다.
//            List<Member> members = em.createQuery("select m from Member m", Member.class)
//                    .getResultList();

        // #1
        List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                .getResultList();
    }

    private static void proxy(EntityManager em) {
        Member member1 = new Member();
        member1.setUsername("member1");
        em.persist(member1);

        em.flush();
        em.clear();

        Member refMember = em.getReference(Member.class, member1.getId());
        System.out.println("refMember.getClass() = " + refMember.getClass());

        Member findMember = em.find(Member.class, member1.getId());
        System.out.println("findMember.getClass() = " + findMember.getClass());

        System.out.println("(refMember == findMember) = " + (refMember == findMember));
    }

    private static void extracted4(EntityManager em) {
        Movie movie = new Movie();
        movie.setDirector("봉준호");
        movie.setActor("최우식");
        movie.setName("기생충");
        movie.setPrice(15000);
        em.persist(movie);
        em.flush();
        em.clear();

        Movie findMovie = em.find(Movie.class, movie.getId());
        System.out.println("findMovie.getName() = " + findMovie.getName());
    }

    private static void extracted3(EntityManager em) {
        Member member = new Member();
        member.setUsername("member1");
        em.persist(member);

        Team team = new Team();
        team.setName("teamA");
        team.getMembers().add(member);
        em.persist(team);
    }

    private static void extracted2(EntityManager em) {
        Team teamA = new Team();
        teamA.setName("teamA");
        em.persist(teamA);

        Member member1 = new Member();
        member1.setUsername("member1");
//            member1.setTeam(teamA);
//            teamA.getMembers().add(member1);
//        member1.changeTeam(teamA);
        em.persist(member1);

//            em.flush();
//            em.clear();

        Team findTeam = em.find(Team.class, teamA.getId());
        findTeam.getMembers().forEach(m -> System.out.println("m.getUsername() = " + m.getUsername()));
    }

    private static void extracted1(EntityManager em) {
        Team team = new Team();
        team.setName("TeamA");
        em.persist(team);

        Member member = new Member();
        member.setUsername("member1");
//            member.setTeamId(team.getId());
//        member.setTeam(team);
        em.persist(member);

        em.flush();
        em.clear();

        // 조회
        Member findMember = em.find(Member.class, member.getId());

        // 연관관계가 없음
//        Team findTeam = member.getTeam();
//        System.out.println("findTeam.getName() = " + findTeam.getName());
    }

    private static void extracted(EntityManager em) {
        /**
         * @GeneratedValue(strategy = GenerationType.IDENTITY)
         * - JPA는 보통 트랜잭션 커밋 시점에 INSERT SQL 실행
         * - INDENTITY 전략은 em.persist() 시점에 즉시 INSERT SQL 실행하고 DB에서 식별자 조회
         *
         * @GeneratedValue(strategy = GenerationType.SEQUENCE)
         * - 쿼리가 버퍼링은 가능하다
         * - em.persist() 시점에 시퀀스 값을 조회하는 쿼리가 발생한다.
         */
        Member member = new Member();
        member.setUsername("haeny");

        System.out.println("==============");
        em.persist(member);
        System.out.println("member.getId() = " + member.getId());
        System.out.println("==============");
    }

    private static void extracted(EntityManager em, EntityTransaction transaction) {
        /* 회원 등록 */
        // 객체를 생성한 상태 -> 비영속
        Member member = new Member();
        member.setId(100L);
        member.setUsername("hello100");

        // 객체를 저장한 상태 -> 영속
        // 1차 캐시에 저장됨
        // 이 순간에 INSERT SQL 를 데이터베이스에 보내지 않는다.
        em.persist(member);

        /* 회원 조회 */
        // 1차 캐시에서 조회
        Member findMember1 = em.find(Member.class, 100L);
        Member findMember2 = em.find(Member.class, 100L);

        /* 영속 엔티티의 동일성 보장 */
        System.out.println("result = " + (findMember1 == findMember2)); // true

            /* 영속상태의 엔티티를 영속성 컨텍스트에서 분리 -> 준영속 상태
//            em.detach(member);

            // 영속 상태
            Member findMember1 = em.find(Member.class, 1L);  // 쿼리 발생
            em.clear(); // 영속성 컨텍스트를 완전히 초기화
            Member findMember2 = em.find(Member.class, 1L);  // 또 다시 쿼리 발생
            */

            /* 회원 삭제
            Member findMember = em.find(Member.class, 1L);
            // 객체를 삭제한 상태 -> 삭제
            em.remove(findMember);*/

        /* 회원 수정 */
        // 영속 엔티티 조회
        Member findMember = em.find(Member.class, 100L);

        // 영속 엔티티 데이터 수정
        findMember.setUsername("HelloEdit");    // dirty checking 을 통해 변경된다...

        // 이런 코드가 있어야 하지 않을까..?
        // em.update(findMember)

        /* JPQL -> 플러시 자동 호출 */
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .setFirstResult(1)
                .setMaxResults(10)
                .getResultList();

        for (Member resultMember : result) {
            System.out.println("resultMember.getName() = " + resultMember.getUsername());
        }

        // Commit 하는 순간 flush 발생
        // -> 데이터베이스에 SQL Query 를 보낸다.
        // -> 트랜잭션을 지원하는 쓰기 지연
        transaction.commit();

        /* 플러시 모드 옵션
         * FlushModeType.AUTO: Commit 이나 Query를 실행할 때 플러시 (기본값)
         * FlushModeType.COMMIT: Commit 할 때만 플러시 */
    }
}
