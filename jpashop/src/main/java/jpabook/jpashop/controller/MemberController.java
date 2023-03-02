package jpabook.jpashop.controller;

import java.util.List;
import javax.validation.Valid;
import jpabook.jpashop.controller.dto.MemberForm;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(final Model model) {
        model.addAttribute("memberForm", new MemberForm());

        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid final MemberForm form, final BindingResult result) {
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Member build = Member.builder()
                .name(form.getName())
                .address(form.getAddress())
                .build();
        memberService.join(build);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(final Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }

}
