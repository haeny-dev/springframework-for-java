package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        // 엔티티 매니저는 데이터 변경시 트랜잭션을 시작해야 한다.
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();    // 트랜잭션 시작

        try {
            /* 회원 등록 */
            // 객체를 생성한 상태 -> 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("hello100");

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
            Member findMember = em.find(Member.class, 1L);

            // 영속 엔티티 데이터 수정
            findMember.setName("HelloEdit");    // dirty checking 을 통해 변경된다...

            // 이런 코드가 있어야 하지 않을까..?
            // em.update(findMember)

            /* JPQL -> 플러시 자동 호출 */
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member resultMember : result) {
                System.out.println("resultMember.getName() = " + resultMember.getName());
            }

            // Commit 하는 순간 flush 발생
            // -> 데이터베이스에 SQL Query 를 보낸다.
            // -> 트랜잭션을 지원하는 쓰기 지연
            transaction.commit();

            /* 플러시 모드 옵션
             * FlushModeType.AUTO: Commit 이나 Query를 실행할 때 플러시 (기본값)
             * FlushModeType.COMMIT: Commit 할 때만 플러시 */
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
