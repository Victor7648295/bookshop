package by.bookshop.bookshop.service;
import by.bookshop.bookshop.model.Order;
import by.bookshop.bookshop.repository.BasketRepository;
import by.bookshop.bookshop.repository.BookRepository;
import by.bookshop.bookshop.repository.OrderRepository;
import by.bookshop.bookshop.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    final
    BasketRepository basketRepository;
    final
    BookRepository bookRepository;
    final
    UserRepository userRepository;
    final
    OrderRepository orderRepository;

    public OrderServiceImpl(UserRepository userRepository, OrderRepository orderRepository, BookRepository bookRepository, BasketRepository basketRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.basketRepository = basketRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        log.info("save order" + order);
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) {
      return  orderRepository.getOne(id);
    }

    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
          orderRepository.deleteById(id);
        log.info("delete order");
    }

    @Override
    public Order updateOrder(Order order) {
        log.info("update order" + order);
        return orderRepository.save(order);
    }


}
