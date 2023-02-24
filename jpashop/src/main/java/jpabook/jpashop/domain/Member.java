package jpabook.jpashop.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import jpabook.jpashop.domain.order.Order;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    private Member(final String name, final Address address) {
        this.name = name;
        this.address = address;
    }

    public static Member createMember(final String name, final Address address) {
        return Member.builder()
                .name(name)
                .address(address)
                .build();
    }
}
