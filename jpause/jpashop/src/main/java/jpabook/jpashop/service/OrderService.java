package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.DeliveryStatus;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.order.Order;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.dto.OrderSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    public OrderService(final OrderRepository orderRepository, final MemberRepository memberRepository,
                        final ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.memberRepository = memberRepository;
        this.itemRepository = itemRepository;
    }

    /**
     * 주문 <br> Order에서만 Delivery와 OrderItem을 사용하고 영속성되는 라이프사이클이 동일하기 때문에 <br> 내부에서 CacadeType.ALL로 두어 Order가 영속화되면 같이
     * 영속화되도록 함 <br> 만약 헷갈린다면 사용하지 말자!
     */
    @Transactional
    public Long order(final Long memberId, final Long itemId, final int count) {
        // 엔티티 조회
        Member member = memberRepository.findById(memberId);
        Item item = itemRepository.findById(itemId);

        // 배송정보 생성
        Delivery delivery = Delivery.builder()
                .address(member.getAddress())
                .status(DeliveryStatus.READY)
                .build();

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 취소
     */
    @Transactional
    public void cancelOrder(final Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findById(orderId);
        // 주문 취소: 내부에서 재고, 상태 등 엔티티의 상태가 변경된다. sql을 직접 사용하면 해당 변경을 update 직접 해주어야 하지만
        // jpa를 사용하면 sql을 직접다루지 않고 더티체킹을 통해 변경내역을 감지하고 DB 쿼리를 자동 요청한다.
        order.cancel();
    }

    // 검색
    public List<Order> findOrders(final OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }

}
