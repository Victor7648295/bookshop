package by.bookshop.bookshop.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="users")
@ToString
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PositiveOrZero
    @Column(name = "user_id")
    private long id;
    @Column(name = "login")
    @NotBlank
    private String login;
    @Column(name = "password")
    @NotBlank
    private String password;
    @Column(name = "token")
    private String token;

    @Column(name = "balance")
    private long balance;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    public User(String login, String password) {
    }
}



