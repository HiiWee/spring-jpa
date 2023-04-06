package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    @Transactional
    public Long join(final Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        // persist되면 기존 id가 null이었어도 jpa에서 id값을 채워줌
        return member.getId();
    }

    private void validateDuplicateMember(final Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("[ERROR] 이미 존재하는 회원 입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(final Long memberId) {
        return memberRepository.findById(memberId);
    }

}
