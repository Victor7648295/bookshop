package by.bookshop.bookshop.repository;

import by.bookshop.bookshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByLoginAndPassword(String login, String password);
    public User findUserByToken(String token);

}
