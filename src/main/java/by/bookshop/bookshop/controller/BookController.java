package by.bookshop.bookshop.controller;
import by.bookshop.bookshop.exception.NotFoundException;
import by.bookshop.bookshop.model.Book;
import by.bookshop.bookshop.service.BookServiceImpl;
import by.bookshop.bookshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/book")
public class BookController {
    final
    UserServiceImpl userServiceImpl;
    final
    BookServiceImpl bookServiceimpl;
@Autowired
    public BookController(BookServiceImpl bookService, UserServiceImpl userServiceImpl) {
        this.bookServiceimpl = bookService;
    this.userServiceImpl = userServiceImpl;
}

    @PostMapping(value = "/add")
    public ResponseEntity<Book> addBook(@RequestBody  Book book){
        this.bookServiceimpl.saveBook(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete/{bookId}")
    public ResponseEntity<Book> deleteBook(@PathVariable("bookId")Long id){
        Book deleteThisBook = bookServiceimpl.getBookById(id);
        if(deleteThisBook == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookServiceimpl.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<Book> findBook(@PathVariable("bookId")Long id){
        Book getBook = bookServiceimpl.getBookById(id);
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        bookServiceimpl.getBookById(id);
        return new ResponseEntity<>(getBook,HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Book>> findAllBooks(){
        List<Book> allBooks = bookServiceimpl.getAllBooks();
        if(allBooks == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(allBooks,HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Book> updateBook(@RequestBody Book book) throws NotFoundException {
        if(bookServiceimpl.getBookById(book.getBookId()) == null)throw new NotFoundException("Not found book");
        bookServiceimpl.saveBook(book);
        return  new ResponseEntity<>(book,HttpStatus.OK);

    }
}

