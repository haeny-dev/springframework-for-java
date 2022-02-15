package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
        /**
         * em.persist 를 하는 순간 영속성 컨텍스트에 해당 멤버를 올린다.
         * 영속성 컨텍스트에 Key, Value 값으로 들어갈 때 Key 가 id 값이 된다.
         * DB에 값이 들어간 상태가 아니더라도, id 값이 생성되어 있는게 보장이 된다.
         */
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
