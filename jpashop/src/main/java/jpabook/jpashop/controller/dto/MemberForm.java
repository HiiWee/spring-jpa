package jpabook.jpashop.controller.dto;

import javax.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String name;
    private Address address;

    public MemberForm() {
        this.address = new Address();
    }

    // 생성자 추가
    public MemberForm(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "MemberForm{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}

