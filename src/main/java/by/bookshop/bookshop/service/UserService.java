package by.bookshop.bookshop.service;
import by.bookshop.bookshop.model.Order;
import by.bookshop.bookshop.model.User;
import java.util.List;

public interface UserService {
    public User findById(Long id);
    public List<User> findAllUsers();
    public void saveUser(User user);
    public void deleteUser(long id);
    public User getLogin(String login , String password);
    public User findUser(Order order);
    public boolean login(String token);
    public User findUserByLoginAndPassword(String login, String password);
    }

