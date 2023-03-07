package jpabook.jpashop.controller;

import javax.validation.Valid;
import jpabook.jpashop.controller.dto.BookForm;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController(final ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items/new")
    public String createForm(final Model model) {
        model.addAttribute("bookForm", BookForm.getEmptyForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(@Valid final BookForm bookForm, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "items/createItemForm";
        }
        Book book = bookForm.createBook();
        itemService.saveItem(book);
        return "redirect:/";
    }

}
