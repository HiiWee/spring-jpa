package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.service.dto.UpdateBookForm;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(final ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void saveItem(final Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findById(final Long itemId) {
        return itemRepository.findById(itemId);
    }

    @Transactional
    public void updateItem(final UpdateBookForm updateBookForm) {
        Item findItem = itemRepository.findById(updateBookForm.getId());
        if (findItem instanceof Book) {
            Book book = (Book) findItem;
            book.changeBook(updateBookForm.getName(), updateBookForm.getPrice(), updateBookForm.getStockQuantity(),
                    updateBookForm.getAuthor(), updateBookForm.getIsbn());
        }
    }
}
