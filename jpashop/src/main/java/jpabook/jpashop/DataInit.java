package jpabook.jpashop;

import javax.annotation.PostConstruct;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import org.springframework.stereotype.Component;

@Component
public class DataInit {

    private final MemberService memberService;
    private final ItemService itemService;

    public DataInit(final MemberService memberService, final ItemService itemService) {
        this.memberService = memberService;
        this.itemService = itemService;
    }

    @PostConstruct
    public void init() {
        Member member1 = Member.builder()
                .name("test1")
                .address(new Address("Seoul", "PanGyo", "12312"))
                .build();
        Member member2 = Member.builder()
                .name("test2")
                .address(new Address("temp", "temp", "22222"))
                .build();
        Book book1 = Book.builder()
                .name("Book1")
                .price(15000)
                .stockQuantity(10)
                .author("test")
                .isbn("123124")
                .build();
        Book book2 = Book.builder()
                .name("Book2")
                .price(20000)
                .stockQuantity(15)
                .author("test2")
                .isbn("13123123")
                .build();

        memberService.join(member1);
        memberService.join(member2);
        itemService.saveItem(book1);
        itemService.saveItem(book2);
    }
}
