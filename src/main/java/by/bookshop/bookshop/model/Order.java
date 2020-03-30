package by.bookshop.bookshop.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @PositiveOrZero
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private long idOrder;
    @Column(name = "user_id")
    private long idUser;
    @Column(name = "basket_id")
    private long idBasket;
    @Column(name = "phone_user")
    private String phone;
    @Column(name = "total_price")
    private long totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public enum Status {
        formed,
        sent
    }
}
