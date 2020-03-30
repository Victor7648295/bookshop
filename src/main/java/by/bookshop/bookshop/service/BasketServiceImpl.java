package by.bookshop.bookshop.service;
import by.bookshop.bookshop.model.Basket;
import by.bookshop.bookshop.repository.BasketRepository;
import by.bookshop.bookshop.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class BasketServiceImpl implements BasketService {

    final
    BookRepository bookRepository;
    final
    BasketRepository basketRepository;

    public BasketServiceImpl(BookRepository bookRepository, BasketRepository basketRepository) {
        this.bookRepository = bookRepository;
        this.basketRepository = basketRepository;
    }

    @Override
    public List<Basket> getAllBasket() {
        return basketRepository.findAll();
    }

    @Override
    public void deleteBasket(Long id) {
      Basket basket =   basketRepository.getOne(id);
      basketRepository.delete(basket);
      log.info("basket delete " + basket);

    }

    @Override
    public Basket addBookInBasket(Basket basket) {
      /* Basket basket = new Basket(id,price);*/
        log.info("book add in basket " + basket);
       return basketRepository.save(basket);

    }

    @Override
    public Basket getBasket(Long id) {
        return basketRepository.getOne(id);
    }

}
