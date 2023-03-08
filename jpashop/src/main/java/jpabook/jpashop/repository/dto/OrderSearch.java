package jpabook.jpashop.repository.dto;

import jpabook.jpashop.domain.order.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class OrderSearch {

    private String memberName; // 회원 이름
    private OrderStatus orderStatus; // 주문 상태[ORDER, CANCEL]

    public OrderSearch(final String memberName, final OrderStatus orderStatus) {
        this.memberName = memberName;
        this.orderStatus = orderStatus;
    }

}
