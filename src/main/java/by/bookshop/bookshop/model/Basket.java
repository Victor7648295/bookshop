package by.bookshop.bookshop.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "basket")
public class Basket {
    @Id
    @PositiveOrZero
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_basket")
    private Long id;
    @Column(name = "id_book")
    private Long idBooks;
    @Column(name = "total_cost")
    private Long totalCost;

    public Basket(Long id, Long price) {
    }
}
