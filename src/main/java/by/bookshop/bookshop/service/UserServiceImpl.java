package by.bookshop.bookshop.service;
import by.bookshop.bookshop.model.Order;
import by.bookshop.bookshop.model.User;
import by.bookshop.bookshop.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    final
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getLogin(String login, String password) {
        return userRepository.findUserByLoginAndPassword(login, password);
    }
    public User findUser(Order order){
        return userRepository.getOne(order.getIdUser());
    }
    @Override
    public boolean login(String token) {
        User user = userRepository.findUserByToken(token);
       return user.getToken().equals(token);
    }
    public User findUserByLoginAndPassword(String login, String password){
        return userRepository.findUserByLoginAndPassword(login,password);
    }



    public boolean getUserToken(String token){
        User user = userRepository.findUserByToken(token);
        return user.getToken() != null;
    }
}
