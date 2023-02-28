package jpabook.jpashop.repository;

import javax.persistence.EntityManager;
import jpabook.jpashop.domain.order.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    private final EntityManager entityManager;

    public OrderRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(final Order order) {
        entityManager.persist(order);
    }

    public Order findById(final Long id) {
        return entityManager.find(Order.class, id);
    }

//    public List<Order> findAll(final OrderSearch orderSearch) {}

}
