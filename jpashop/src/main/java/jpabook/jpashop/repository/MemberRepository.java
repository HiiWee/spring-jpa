package jpabook.jpashop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(final Member member) {
        entityManager.persist(member);
    }

    public Member findById(final Long id) {
        return entityManager.find(Member.class, id);
    }

    public List<Member> findAll() {
        // JPQL은 테이블이 아닌 엔티티 객체를 대상으로 쿼리를 날림
        return entityManager.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(final String name) {
        return entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
