package ee.ivkhkdev.nptv23javafx.model.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private AppUser user;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Book book;
    @Temporal(TemporalType.DATE)
    private LocalDate takeOnDate;
    @Temporal(TemporalType.DATE)
    private LocalDate returnDate;

    public History() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getTakeOnDate() {
        return takeOnDate;
    }

    public void setTakeOnDate(LocalDate takeOnDate) {
        this.takeOnDate = takeOnDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return Objects.equals(id, history.id) && Objects.equals(user, history.user) && Objects.equals(book, history.book) && Objects.equals(takeOnDate, history.takeOnDate) && Objects.equals(returnDate, history.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, book, takeOnDate, returnDate);
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", user=" + user.getFirstname() + " " + user.getLastname() +
                ", book=" + book.getTitle() + " " + book.getCount() +
                ", takeOnDate=" + takeOnDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
