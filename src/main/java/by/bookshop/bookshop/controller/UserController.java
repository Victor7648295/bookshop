package by.bookshop.bookshop.controller;
import by.bookshop.bookshop.exception.AuthException;
import by.bookshop.bookshop.exception.NotFoundException;
import by.bookshop.bookshop.model.User;
import by.bookshop.bookshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    final
    UserServiceImpl userServiceImpl;
@Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }
    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> getAllUsers() {
        if(userServiceImpl.findAllUsers() == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<User> users = userServiceImpl.findAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long userId,
                                        @RequestBody User newUser) throws NotFoundException, AuthException {
        if (!userServiceImpl.login(newUser.getToken())) throw new AuthException("User not authorized");
        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userServiceImpl.findById(userId);
        if (user == null)throw new NotFoundException("User not found");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/reg")
    public ResponseEntity<User> saveUser(@RequestBody User newUser) throws AuthException {
    User user = userServiceImpl.findUserByLoginAndPassword(newUser.getLogin(),newUser.getPassword());
    if(user != null) throw new AuthException("login  already exist");
        userServiceImpl.saveUser(newUser);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/delete{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) throws NotFoundException {
    User user = userServiceImpl.findById(id);
        if (user == null) throw new NotFoundException("User not found");
        this.userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<User> updateUser(@RequestBody User newUser) throws AuthException {
        User user =  userServiceImpl.findUserByLoginAndPassword(newUser.getLogin(),newUser.getPassword());
        if (!user.getToken().equals(newUser.getToken())) throw new AuthException("User not authorized");
        this.userServiceImpl.saveUser(newUser);
        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<User> auth(@RequestBody User newUser) throws  AuthException {
           User user1 =  userServiceImpl.findUserByLoginAndPassword(newUser.getLogin(),newUser.getPassword());
        if (user1.getToken()!= null) throw new AuthException("User is already authorized");
        List<User> users = userServiceImpl.findAllUsers();
        for (User listUsers : users) {
            if (listUsers.getLogin().equals(newUser.getLogin()) && listUsers.getPassword().equals(newUser.getPassword())) {
                User user = userServiceImpl.findUserByLoginAndPassword(newUser.getLogin(), newUser.getPassword());
                user.setToken(Integer.toString(new Random().nextInt()));
                userServiceImpl.saveUser(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<User> logout(@RequestBody User newUser) throws AuthException, NotFoundException {
        if (!userServiceImpl.login(newUser.getToken())) throw new AuthException("User not authorized");
        if (userServiceImpl.findUserByLoginAndPassword(newUser.getLogin(), newUser.getPassword()) == null) throw new NotFoundException("User not found");
        User user = userServiceImpl.findUserByLoginAndPassword(newUser.getLogin(), newUser.getPassword());
        user.setToken(null);
        userServiceImpl.saveUser(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}