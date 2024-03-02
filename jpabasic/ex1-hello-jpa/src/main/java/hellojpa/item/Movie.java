package hellojpa.item;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "M")
public class Movie extends Item {

    private String director;
    private String actor;

    public Movie() {
    }

    public Movie(final String director, final String actor, final String name, final int price) {
        super(name, price);
        this.director = director;
        this.actor = actor;
    }
}
