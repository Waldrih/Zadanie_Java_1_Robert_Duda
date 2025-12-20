package pl.wszib.edu.duda.database;

import org.springframework.stereotype.Component;
import pl.wszib.edu.duda.exceptions.CanNotRentBookException;
import pl.wszib.edu.duda.exceptions.CanNotReturnBookException;
import pl.wszib.edu.duda.model.Book;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookRepository implements IBookRepository {

    private final List<Book> books = new ArrayList<>();

    public BookRepository() {
        books.add(new Book("Warhammer 40k: Core Rulebook", "Games Workshop"));
        books.add(new Book("Horus Heresy: Horus Rising", "Dan Abnett"));
        books.add(new Book("Horus Heresy: False Gods", "Graham McNeill"));
        books.add(new Book("Warhammer RPG", "Games Workshop"));
        books.add(new Book("Siege of Terra: The Solar War", "John French"));
        books.add(new Book("abc", "def"));
    }

    //CRUD

    @Override
    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public void removeBook(Book book) {
        this.books.remove(book);
    }

    @Override
    public void updateBook(Book updatedBook) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(updatedBook.getTitle())) {
                b.setAuthor(updatedBook.getAuthor());
                return;
            }
        }
        throw new RuntimeException("Book not found to update");
    }

    //RENT / RETURN

    @Override
    public void rentBook(Book book) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle()) && b.isAvailable()) {
                b.setAvailable(false);
                return;
            }
        }
        throw new CanNotRentBookException();
    }

    @Override
    public void returnBook(Book book) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(book.getTitle()) && !b.isAvailable()) {
                b.setAvailable(true);
                return;
            }
        }
        throw new CanNotReturnBookException();
    }

    //READ

    @Override
    public List<Book> getBooks() {
        return new ArrayList<>(this.books);
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        return result;
    }
}
