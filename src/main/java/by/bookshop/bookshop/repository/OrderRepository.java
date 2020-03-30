package by.bookshop.bookshop.repository;

import by.bookshop.bookshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
