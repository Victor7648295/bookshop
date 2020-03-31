package by.bookshop.bookshop.service;
import by.bookshop.bookshop.model.Order;
import by.bookshop.bookshop.model.User;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public Order saveOrder(Order order);
    public Order findById(Long id);
    public List<Order> findAllOrder();
    public void deleteOrder(Long id);
    public Order updateOrder(Order order);
    public List<String> sent(Order order);

}
