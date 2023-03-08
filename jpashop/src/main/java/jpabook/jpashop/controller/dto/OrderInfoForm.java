package jpabook.jpashop.controller.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OrderInfoForm {

    @NotNull(message = "주문할 사용자를 선택해야 합니다.")
    private Long memberId;

    @NotNull(message = "주문할 상품을 선택해야 합니다.")
    private Long itemId;

    @Min(value = 1, message = "1개 이상 주문해야 합니다.")
    private int count;

    private OrderInfoForm() {
    }

    public OrderInfoForm(final Long memberId, final Long itemId, final int count) {
        this.memberId = memberId;
        this.itemId = itemId;
        this.count = count;
    }

    public static OrderInfoForm getEmptyForm() {
        return new OrderInfoForm();
    }

    @Override
    public String toString() {
        return "OrderInforForm{" +
                "memberId=" + memberId +
                ", itemId=" + itemId +
                ", count=" + count +
                '}';
    }
}
