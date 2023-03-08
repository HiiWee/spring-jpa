package jpabook.jpashop.controller;

import java.util.List;
import javax.validation.Valid;
import jpabook.jpashop.controller.dto.OrderInfoForm;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    public OrderController(final OrderService orderService, final MemberService memberService,
                           final ItemService itemService) {
        this.orderService = orderService;
        this.memberService = memberService;
        this.itemService = itemService;
    }

    @GetMapping("/order")
    public String createForm(final Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("orderInfoForm", OrderInfoForm.getEmptyForm());
        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String create(@Valid final OrderInfoForm orderInfoForm, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "order/orderForm";
        }
        orderService.order(orderInfoForm.getMemberId(), orderInfoForm.getItemId(), orderInfoForm.getCount());
        return "redirect:/orders";
    }
}
