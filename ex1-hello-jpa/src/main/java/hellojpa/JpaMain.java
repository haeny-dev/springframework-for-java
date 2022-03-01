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
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /* 회원 등록
            Member member = new Member();
            member.setId(2L);
            member.setName("hello2");
            em.persist(member);*/

            /* 회원 조회
            Member findMember = em.find(Member.class, 1L);
            System.out.println("findMember.getId() = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName());*/

            /* 회원 삭제
            Member findMember = em.find(Member.class, 1L);
            em.remove(findMember);*/

            /* 회원 수정
            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloEdit");*/

            /* JPQL */
            List<Member> result = em.createQuery("select m from Member m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();

            for (Member findMember : result) {
                System.out.println("findMember.getName() = " + findMember.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
