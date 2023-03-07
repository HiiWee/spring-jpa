package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("B")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book extends Item {
    private String author;
    private String isbn;

    @Builder
    private Book(final Long id, final String name, final int price, final int stockQuantity, final String author, final String isbn) {
        super(id, name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }

    public void changeBook(final String name, final int price, final int stockQuantity, final String author,
                           final String isbn) {
        if (name == null || author == null || isbn == null) {
            throw new IllegalArgumentException("상품을 수정할 수 없습니다.");
        }
        super.change(name, price, stockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
