package jpabook.jpashop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.domain.order.OrderStatus;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    Member member;

    Item item;

    @BeforeEach
    void setUp() {
        member = Member.builder()
                .name("회원1")
                .address(new Address("서울", "강가", "123-123"))
                .build();

        item = Book.builder()
                .name("시골 JPA")
                .price(10000)
                .stockQuantity(10)
                .build();

        entityManager.persist(member);
        entityManager.persist(item);
    }

    @Test
    @DisplayName("상품 주문 테스트")
    void create_order() {
        // given
        int orderCount = 2;

        // when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
        Order findOrder = orderRepository.findById(orderId);

        // then
        Assertions.assertAll(
                () -> assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.ORDER),
                () -> assertThat(findOrder.getOrderItems().size()).isEqualTo(1),
                () -> assertThat(findOrder.getTotalPrice()).isEqualTo(10000 * orderCount),
                () -> assertThat(item.getStockQuantity()).isEqualTo(8)
        );
    }

    @Test
    @DisplayName("상품주문 재고수량 초과")
    void orderProduct_outOfStock() {
        // given
        int orderCount = 11;

        // then
        assertThatThrownBy(() -> orderService.order(member.getId(), item.getId(), orderCount))
                .isInstanceOf(NotEnoughStockException.class)
                .hasMessageContaining("재고가 부족합니다.");
    }

    @Test
    void cancel_order() {
        // given
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        // when
        orderService.cancelOrder(orderId);
        Order findOrder = orderRepository.findById(orderId);

        // then
        Assertions.assertAll(
                () -> assertThat(findOrder.getStatus()).isEqualTo(OrderStatus.CANCEL),
                () -> assertThat(item.getStockQuantity()).isEqualTo(10)
        );

    }

}