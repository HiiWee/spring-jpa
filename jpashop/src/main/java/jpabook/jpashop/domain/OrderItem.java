package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.order.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; // 주문 가격

    private int count; // 주문 수량

    @Builder
    public OrderItem(final Item item, final Order order, final int orderPrice, final int count) {
        this.item = item;
        this.order = order;
        this.orderPrice = orderPrice;
        this.count = count;
    }

    public static OrderItem createOrderItem(final Item item, final Order order, final int orderPrice, final int count) {
        return OrderItem.builder()
                .item(item)
                .order(order)
                .orderPrice(orderPrice)
                .count(count)
                .build();
    }

    public void addOrder(final Order order) {
        this.order = order;
    }

}
