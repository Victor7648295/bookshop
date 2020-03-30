package by.bookshop.bookshop.service;
import by.bookshop.bookshop.model.Book;
import by.bookshop.bookshop.model.Order;
import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();
    public Book getBookById(Long id);
    public void saveBook(Book book);
    public void deleteBook(Long id);
    public Book findBook(Order order);
}
