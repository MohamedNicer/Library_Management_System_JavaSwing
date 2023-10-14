import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Borrowing {
    LocalDate start;
    LocalDate finish;
    int daysleft;
    User user;
    Book book;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Borrowing(User user, Book book) {
        start = LocalDate.now();
        finish = start.plusDays(14);
        daysleft = Period.between(start, finish).getDays();
        this.book = book;
        this.user = user;
    }

    public Borrowing(LocalDate start, LocalDate finish, User user, Book book) {
        this.start = start;
        this.finish = finish;
        this.user = user;
        this.book = book;
        daysleft = Period.between(start, finish).getDays();

    }

    public String getStart() {
        return formatter.format(start);
    }

    public String getFinish() {
        return formatter.format(finish);
    }

    public int getDaysleft() {
        return Period.between(start, finish).getDays();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Borrowing time: " + start + "InExpiry date: " + finish + "\nDays left: " + daysleft;
    }
}

