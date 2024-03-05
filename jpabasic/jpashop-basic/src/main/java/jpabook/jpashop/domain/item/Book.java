package jpabook.jpashop.domain.item;

import jakarta.persistence.Entity;

@Entity
public class Book extends Item {

    private String author;
    private String isbn;

    protected Book() {
    }

    public Book(final String name, final int price, final Long stockQuantity, final String author, final String isbn) {
        super(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
