package jpabook.jpashop.domain.item;

import jakarta.persistence.Entity;

@Entity
public class Movie extends Item {

    private String director;
    private String actor;

    protected Movie() {
    }

    public Movie(final String name, final int price, final Long stockQuantity, final String director, final String actor) {
        super(name, price, stockQuantity);
        this.director = director;
        this.actor = actor;
    }
}
