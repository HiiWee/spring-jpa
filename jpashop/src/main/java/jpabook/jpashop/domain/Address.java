package jpabook.jpashop.domain;

import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 직접 생성을 방지하기 위해, JPA 스펙은 PROTECTED까지 허용해줌
@AllArgsConstructor
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
