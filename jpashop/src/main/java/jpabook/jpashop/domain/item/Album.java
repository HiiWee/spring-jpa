package jpabook.jpashop.domain.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("A")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Album extends Item {
    private String artist;
    private String etc;

    @Builder
    private Album(final String name, final int price, final int stockQuantity, final String artist, final String etc) {
        super(name, price, stockQuantity);
        this.artist = artist;
        this.etc = etc;
    }

    public static Album createAlbum(final String name, final int price, final int stockQuantity, final String artist,
                                    final String etc) {
        return Album.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .artist(artist)
                .etc(etc)
                .build();
    }
}