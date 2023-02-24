package jpabook.jpashop.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class ItemRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    ItemRepository itemRepository;

    Album album;
    Book book;
    Movie movie;

    @BeforeEach
    void setUp() {
        itemRepository = new ItemRepository(testEntityManager.getEntityManager());
        album = Album.builder()
                .name("album")
                .build();
        book = Book.builder()
                .name("book")
                .build();
        movie = Movie.builder()
                .name("movie").build();

        itemRepository.save(album);
        itemRepository.save(book);
        itemRepository.save(movie);
    }

    @Test
    void findById() {
        // when
        Item item = itemRepository.findById(album.getId());

        // then
        assertThat(item).isEqualTo(album);
    }

    @Test
    void finaAll() {
        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items).containsExactly(album, book, movie);
    }
}