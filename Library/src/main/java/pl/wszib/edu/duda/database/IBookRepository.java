package pl.wszib.edu.duda.database;

import pl.wszib.edu.duda.model.Book;
import java.util.List;

public interface IBookRepository {
    void addBook(Book book);
    void removeBook(Book book);
    void updateBook(Book book);

    void rentBook(Book book);
    void returnBook(Book book);

    List<Book> getBooks();

    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
}
