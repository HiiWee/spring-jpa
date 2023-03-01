package jpabook.jpashop.repository;

import static jpabook.jpashop.domain.QMember.member;
import static jpabook.jpashop.domain.order.QOrder.order;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import jpabook.jpashop.domain.QMember;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderStatus;
import jpabook.jpashop.domain.order.QOrder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class OrderRepository {

    private final EntityManager entityManager;
    private final JPAQueryFactory query;

    public OrderRepository(final EntityManager entityManager) {
        this.entityManager = entityManager;
        query = new JPAQueryFactory(entityManager);
    }

    public void save(final Order order) {
        entityManager.persist(order);
    }

    public Order findById(final Long id) {
        return entityManager.find(Order.class, id);
    }

    //==동적 쿼리 만들기==//

    /**
     * 1. 노가다 문자열 jpql 사용 (비권장)
     */
    public List<Order> findAllByString(final OrderSearch orderSearch) {
        String jpql = "select o from Order o join o.member m";

        // 주문 상태 검색
        boolean isFirstCondition = true;
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = entityManager.createQuery(jpql, Order.class)
                // .setFirstResult(100) // 100개부터 시작해서 최대 1000개 -> 페이징 사용 가능
                .setMaxResults(1000);

        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();
    }

    /**
     * 2. JPA Criteria로 해결하기 (비권장)
     */
    public List<Order> findAllByCriteria(final OrderSearch orderSearch) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> query = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = query.from(Order.class);
        Join<Object, Object> member = orderRoot.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = criteriaBuilder.equal(orderRoot.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        // 회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = criteriaBuilder.like(member.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }
        query.where(criteriaBuilder.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> resultQuery = entityManager.createQuery(query).setMaxResults(1000);
        return resultQuery.getResultList();
    }

    /**
     * 3. QueryDSL 사용
     */
    public List<Order> findAll(final OrderSearch orderSearch) {
        QOrder order = QOrder.order;
        QMember member = QMember.member;

        return query.select(order)
                .from(order)
                .join(order.member, member)
                .where(statusEqual(orderSearch.getOrderStatus()),
                        nameLike(orderSearch.getMemberName()))
                .limit(1000)
                .fetch();
    }

    private BooleanExpression statusEqual(final OrderStatus statusCondition) {
        if (statusCondition == null) {
            return null;
        }
        return order.status.eq(statusCondition);
    }

    private BooleanExpression nameLike(final String nameCondition) {
        if (!StringUtils.hasText(nameCondition)) {
            return null;
        }
        return member.name.like(nameCondition);
    }

}
