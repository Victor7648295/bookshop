package by.bookshop.bookshop.controller;
import by.bookshop.bookshop.exception.AuthException;
import by.bookshop.bookshop.exception.NotFoundException;
import by.bookshop.bookshop.model.Basket;
import by.bookshop.bookshop.model.User;
import by.bookshop.bookshop.service.BasketServiceImpl;
import by.bookshop.bookshop.service.BookServiceImpl;
import by.bookshop.bookshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/basket")
public class BasketController {
    final
    UserServiceImpl userServiceImpl;
    final
    BasketServiceImpl basketService;
    final
    BookServiceImpl bookService;
@Autowired
    public BasketController(BasketServiceImpl basketService, BookServiceImpl bookService, UserServiceImpl userServiceImpl) {
        this.basketService = basketService;
    this.bookService = bookService;
    this.userServiceImpl = userServiceImpl;
}

@GetMapping(value = "/getAll")
    public ResponseEntity<List<Basket>> getAllBasket(@RequestBody User newUser) throws AuthException, NotFoundException {
    if (!userServiceImpl.login(newUser.getToken())) throw new AuthException("User not authorized");
    if(basketService.getAllBasket() == null)throw new NotFoundException("Not Found basket");
    List<Basket> basket = basketService.getAllBasket();
       return new ResponseEntity<>(basket,HttpStatus.OK);
    }

@DeleteMapping (value = "/delete/{id}")
    public ResponseEntity<Basket> deleteBasket(@PathVariable("id") Long id) throws NotFoundException {
    if(basketService.getBasket(id) == null)throw new NotFoundException("Not found basket");
    basketService.deleteBasket(id);
    return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/addBasket")
    public ResponseEntity<Basket> addBook(@RequestBody Basket basket) throws NotFoundException {
    if(basket == null) throw new NotFoundException("Not found basket");
    basketService.addBookInBasket(basket);
    return new ResponseEntity<>(basket,HttpStatus.OK);
    }
}
