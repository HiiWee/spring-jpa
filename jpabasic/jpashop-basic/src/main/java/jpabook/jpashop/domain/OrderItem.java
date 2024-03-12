package jpabook.jpashop.domain;


import static jakarta.persistence.FetchType.LAZY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jpabook.jpashop.domain.common.BaseEntity;
import jpabook.jpashop.domain.item.Item;

@Entity
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    public Long getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public Order getOrder() {
        return order;
    }

    public void addOrder(final Order order) {
        this.order = order;
    }
}
