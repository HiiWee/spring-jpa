package jpabook.jpashop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void join() {
        // given
        Member member = Member.builder()
                .name("kim")
                .build();

        // when
        Long savedId = memberService.join(member);

        // then
        entityManager.flush();
        assertThat(member).isEqualTo(memberRepository.findById(savedId));   // 같은 영속성 컨텍스트에서 하나의 객체로 관리됨
    }

    @Test
    void join_exception_duplicated() {
        // given
        Member member1 = Member.builder()
                .name("kim")
                .build();
        Member member2 = Member.builder()
                .name("kim")
                .build();

        // when
        memberService.join(member1);

        // then
        assertThatThrownBy(() -> memberService.join(member2))
                .isInstanceOf(IllegalStateException.class);
    }
}