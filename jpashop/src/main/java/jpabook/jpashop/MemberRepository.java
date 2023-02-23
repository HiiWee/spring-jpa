package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Command와 Query를 분리하라 -> 저장하고 나면 사이드 이펙트를 발생시킬 수 있는 커맨드이므로 리턴값을 거의 만들지 않음
    // 대신 id 정도 있으면 다음에 조회할 수 있으므로 반환
    public Long save(final Member member) {
        entityManager.persist(member);
        return member.getId();
    }

    public Member find(final Long id) {
        return entityManager.find(Member.class, id);
    }
}
