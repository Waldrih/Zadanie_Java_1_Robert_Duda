package pl.wszib.edu.duda.database;

import org.springframework.stereotype.Component;
import pl.wszib.edu.duda.exceptions.CanNotRentBookException;
import pl.wszib.edu.duda.exceptions.CanNotReturnBookException;
import pl.wszib.edu.duda.model.Book;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository implements IBookRepository{

    private final List<Book> books = new ArrayList<>();

    public BookRepository(){
        this.books.add(new Book("Warhammer 40k: Core Rulebook", "Games Workshop"));
        this.books.add(new Book("Horus Heresy: Horus Rising", "Dan Abnett"));
        this.books.add(new Book("Horus Heresy: False Gods", "Graham McNeill"));
        this.books.add(new Book("Warhammer RPG", "Games Workshop"));
        this.books.add(new Book("Siege of Terra: The Solar War", "John French"));

    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void removeBook(String isbn) {

    }

    @Override
    public void removeBook(Book book) {

    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public void rentBook(Book book) {
            for(Book b : this.books){
                if (b.getTitle().equals(book.getTitle()) && book.isAvailable()) {
                    b.setAvailable(false);
                    return;
                }
            }
            throw new CanNotRentBookException();
    }

    @Override
    public void returnBook(Book book) {
        for (Book b : this.books) {
            if (b.getTitle().equals(book.getTitle()) && !b.isAvailable()) {
                b.setAvailable(true);
                return;
            }
        }
        throw new CanNotReturnBookException();
    }


    @Override
    public List<Book> getBooks() {
        return new ArrayList<>(this.books); // zwraca kopię listy
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : this.books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : this.books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }

}
