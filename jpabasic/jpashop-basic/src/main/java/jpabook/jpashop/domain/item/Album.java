package jpabook.jpashop.domain.item;

import jakarta.persistence.Entity;

@Entity
public class Album extends Item {

    private String artist;
    private String etc;

    protected Album() {
    }

    public Album(final String name, final int price, final Long stockQuantity, final String artist, final String etc) {
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }
}
