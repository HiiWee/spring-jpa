package hellojpa.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "B")
public class Book extends Item {

    private String author;
    private String isbn;
}
