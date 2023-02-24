package jpabook.jpashop.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderItem;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dellivery_id")
    private Delivery delivery;  // 자주 조회되는곳에 외래키를 둔다.

    // 자바8 클래스라 하이버네이트가 알아서 지원해줌
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    private Order(final Member member, final Delivery delivery, final OrderStatus status) {
        this.member = member;
        this.delivery = delivery;
        this.status = status;
        //==연관관계 편의 메소드==//
        // 연관관계 편의 메소드의 위치는 양쪽 중에서 핵심적인 컨트롤을 하는 객체가 들고있는것이 좋다.
        member.getOrders().add(this);
        delivery.addOrder(this);
    }

    public static Order createOrder(final Member member, final Delivery delivery, final OrderStatus status) {
        return Order.builder()
                .member(member)
                .delivery(delivery)
                .status(status)
                .build();
    }

    public void addOrderItem(final OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.addOrder(this);
    }

}
