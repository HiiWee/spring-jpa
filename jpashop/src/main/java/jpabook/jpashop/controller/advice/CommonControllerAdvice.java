package jpabook.jpashop.controller.advice;

import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(NotEnoughStockException.class)
    public String handleNotEnoughStockException(final NotEnoughStockException e, final Model model) {
        log.error(e.getMessage());
        model.addAttribute("errorMessage", e.getMessage());
        return "error/notEnoughStock";
    }
}
