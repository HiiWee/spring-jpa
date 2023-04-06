package jpabook.jpashop.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    Album album;

    Book book;

    Movie movie;

    @BeforeEach
    void setUp() {

        album = Album.builder()
                .name("album")
                .build();
        book = Book.builder()
                .name("book")
                .build();
        movie = Movie.builder()
                .name("movie").build();

        itemService.saveItem(album);
        itemService.saveItem(book);
        itemService.saveItem(movie);
    }

    @Test
    void findItems() {
        // when
        List<Item> items = itemService.findItems();

        // then
        assertThat(items).containsExactly(album, book, movie);
    }

    @Test
    void findById() {
        // then
        assertThat(album).isEqualTo(itemService.findById(album.getId()));
    }
}