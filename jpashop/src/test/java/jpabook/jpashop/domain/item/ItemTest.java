package jpabook.jpashop.domain.item;

import jpabook.jpashop.exception.NotEnoughStockException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void addStock() {
        // given
        Book book = Book.builder().build();

        // when
        book.addStock(100);

        // then
        Assertions.assertThat(book.getStockQuantity()).isEqualTo(100);
    }

    @Test
    void removeStock() {
        // given
        Book book = Book.builder().build();

        // when
        book.addStock(100);
        book.removeStock(100);

        // then
        Assertions.assertThat(book.getStockQuantity()).isEqualTo(0);
    }

    @Test
    void removeStock_exception_underZeroQuantity() {
        // given
        Book book = Book.builder().build();

        // then
        Assertions.assertThatThrownBy(() -> book.removeStock(100))
                .isInstanceOf(NotEnoughStockException.class);
    }
}