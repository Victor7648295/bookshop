package by.bookshop.bookshop.model;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @PositiveOrZero
    @Column(name = "id_book")
    private long bookId;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "book_genre")
    @Enumerated(EnumType.STRING)
    private Type bookGenre;
    @Column(name = "price")
    private Long price;
    @Column(name = "raiting")
    @Enumerated(EnumType.STRING)
    private Raiting raiting;

    enum Type {
        HORRORS,
        DETECTIVE,
        FANTASY,
        NOVEL
    }

    enum Raiting {
        noStar,
        oneStar,
        twoStar,
        threeStar,
        fourStar,
        fiveStar
    }
}