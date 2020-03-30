package by.bookshop.bookshop.repository;

import by.bookshop.bookshop.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket,Long> {

}
