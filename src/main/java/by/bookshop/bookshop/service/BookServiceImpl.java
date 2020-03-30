package by.bookshop.bookshop.service;
import by.bookshop.bookshop.model.Basket;
import by.bookshop.bookshop.model.Book;
import by.bookshop.bookshop.model.Order;
import by.bookshop.bookshop.repository.BasketRepository;
import by.bookshop.bookshop.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    final
    BasketRepository basketRepository;
    final
    BookRepository bookRepository;

    public BookServiceImpl(BasketRepository basketRepository, BookRepository bookRepository) {
        this.basketRepository = basketRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.getOne(id);
    }

    @Override
    public void saveBook(Book book) {
        bookRepository.save(book);
        log.info("Book save " + book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
        log.info("Book delete ");
    }
    public Book findBook(Order order){
        Basket basket = basketRepository.getOne(order.getIdBasket());
        return bookRepository.getOne(basket.getIdBooks());
    }
}