package jpabook.jpashop.service.dto;

import lombok.Getter;

@Getter
public class UpdateBookForm {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;

    public UpdateBookForm(final Long id, final String name, final int price, final int stockQuantity,
                          final String author,
                          final String isbn) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.author = author;
        this.isbn = isbn;
    }

}
