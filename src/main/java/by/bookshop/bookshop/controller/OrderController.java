package by.bookshop.bookshop.controller;
import by.bookshop.bookshop.exception.AuthException;
import by.bookshop.bookshop.exception.NotFoundException;
import by.bookshop.bookshop.exception.NotMoneyException;
import by.bookshop.bookshop.model.Basket;
import by.bookshop.bookshop.model.Order;
import by.bookshop.bookshop.model.User;
import by.bookshop.bookshop.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import static by.bookshop.bookshop.model.Order.Status.sent;

@RestController
@RequestMapping(value = "/order")
public class OrderController  {
    final
    BookServiceImpl bookService;
    final
    BasketServiceImpl basketService;
    final
    UserServiceImpl userServiceimpl;
    final
    OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService, UserServiceImpl userServiceimpl, BasketServiceImpl basketService, BookServiceImpl bookService) {
        this.orderService = orderService;
        this.userServiceimpl = userServiceimpl;
        this.basketService = basketService;
        this.bookService = bookService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Order>> getAllOrders(@RequestBody User newUser) throws AuthException, NotFoundException {
        if (!userServiceimpl.login(newUser.getToken())) throw new AuthException("User not authorized");
      List<Order> orders =  orderService.findAllOrder();
      if(orders == null ) throw new NotFoundException("Not found orders");
      return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order) throws NotFoundException {
        if(order == null ) throw new NotFoundException("Not found orders");
        orderService.saveOrder(order);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete{id}")
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") long id) throws NotFoundException {
        if(orderService.findById(id) == null ) throw new NotFoundException("Not found orders");
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrders(@PathVariable("id") Long id,
                                           @RequestBody User newUser) throws AuthException {
        if (!userServiceimpl.login(newUser.getToken())) throw new AuthException("User not authorized");
    Order order = orderService.findById(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) throws NotFoundException {
        Order newOrder = orderService.findById(order.getIdOrder());
        if(newOrder == null )throw new NotFoundException("Not found order");
        orderService.saveOrder(order);
        return new ResponseEntity<>(order,HttpStatus.OK);

    }

    @PostMapping(value = "/sent")
    public ResponseEntity<List<String>> sentDelivery(@RequestBody Order order) throws NotMoneyException {
        if(userServiceimpl.findUser(order).getBalance() < bookService.findBook(order).getPrice())throw new NotMoneyException("Not enough funds on balance");
        User newUser = userServiceimpl.findUser(order);
        newUser.setBalance(newUser.getBalance() - bookService.findBook(order).getPrice());
        List<String> list = new ArrayList<>();
      String user = userServiceimpl.findUser(order).toString();
      String  book = bookService.findBook(order).toString();
        list.add(user);
        list.add(book);
        order.setStatus(sent);
        orderService.saveOrder(order);
        Basket basket = basketService.getBasket(order.getIdBasket());
        basketService.deleteBasket(basket.getId());
      return new ResponseEntity<>( list,HttpStatus.OK);
    }
}
