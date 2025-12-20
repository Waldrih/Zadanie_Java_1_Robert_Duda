package pl.wszib.edu.duda.gui;

import pl.wszib.edu.duda.model.Book;
import pl.wszib.edu.duda.model.User;

import java.util.List;

public interface IGUI {
    void showUserMenu();
    void showAdminMenu();
    String readMenuOption();

    String askSearchMode();
    String readBookTitle();
    String readBookAuthor();

    void listBooks(List<Book> books);
    void showRentSuccessMessage(boolean success);
    void showReturnSuccessMessage(boolean success);
    void showWrongOptionMessage();

    User readLoginAndPassword();

    Book readNewBook();
    Book readBookToEdit();
}
