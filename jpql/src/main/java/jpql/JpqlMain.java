package jpql;

import jpql.domain.Member;

import javax.persistence.*;
import java.util.List;

public class JpqlMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpql");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
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

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            em.close();
        }
    }
}
