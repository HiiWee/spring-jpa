package jpabook.jpashop.controller.dto;

import javax.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import lombok.Getter;

@Getter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;
    private String city;
    private String street;
    private String zipcode;

    private MemberForm() {
    }

    public MemberForm(final String name, final String city, final String street, final String zipcode) {
        this.name = name;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    public static MemberForm getEmptyForm() {
        return new MemberForm();
    }

    public Member createMember() {
        return Member.builder()
                .name(name)
                .address(new Address(city, street, zipcode))
                .build();
    }

}

