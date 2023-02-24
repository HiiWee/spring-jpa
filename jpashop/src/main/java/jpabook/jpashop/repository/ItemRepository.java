package jpabook.jpashop.repository;

import java.util.List;
import javax.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

    private final EntityManager entityManager;

    @Autowired
    public ItemRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(final Item item) {
        if (item.getId() == null) {
            entityManager.persist(item);
            return;
        }
        // 이미 영속성 컨텍스트에 존재하는 객체라면 기존에 있던 데이터에 변경사항만 추가한다.
        // 만약 영속성 컨텍스트에서 엔티티를 조회할 수 없다면 새로 생성하고 병합한다.
        entityManager.merge(item);
    }

    public Item findById(final Long id) {
        return entityManager.find(Item.class, id);
    }

    public List<Item> findAll() {
        return entityManager.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
