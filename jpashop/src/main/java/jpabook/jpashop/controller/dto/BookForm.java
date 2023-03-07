package jpabook.jpashop.controller.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.item.Book;
import lombok.Getter;

@Getter
public class BookForm {

    @NotEmpty(message = "이름을 반드시 입력해야 합니다.")
    private String name;

    @Min(value = 1000, message = "가격은 1000원 이상이어야 합니다.")
    private int price;

    @Min(value = 1, message = "최소 1개 이상 등록해야 합니다.")
    private int stockQuantity;

    @NotEmpty(message = "저자를 반드시 입력해야 합니다.")
    private String author;

    private String isbn;

    private BookForm() {
    }

    public BookForm(final String name, final int price, final int stockQuantity, final String author,
                    final String isbn) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }

    public static BookForm getEmptyForm() {
        return new BookForm();
    }

    public Book createBook() {
        return Book.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .author(author)
                .isbn(isbn)
                .build();
    }

}
