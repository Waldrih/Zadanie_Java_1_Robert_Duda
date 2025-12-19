package pl.wszib.edu.duda.gui;

import pl.wszib.edu.duda.model.Book;
import pl.wszib.edu.duda.model.User;

import java.util.List;

public interface IGUI {
    void showUserMenu();                  // case: user
    void showAdminMenu();                 // case: admin
    String readMenuOption();              // zamiast showMenuAndReadChoose

    void listBooks(List<Book> books);     // wyświetlenie książek
    void showRentSuccessMessage(boolean success);
    void showReturnSuccessMessage(boolean success);  // NOWA METODA
    void showWrongOptionMessage();        // przy błędnej opcji

    String readBookTitle();               // do wypożyczania / zwracania
    Book readBookToEdit();                // do edycji książki (admin)

    User readLoginAndPassword();          // logowanie
}
