package pl.wszib.edu.duda.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wszib.edu.duda.authentication.IAuthenticator;
import pl.wszib.edu.duda.database.IBookRepository;
import pl.wszib.edu.duda.gui.IGUI;
import pl.wszib.edu.duda.model.Book;
import pl.wszib.edu.duda.model.User;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Core implements ICore {
    private final IBookRepository bookRepository;
    private final IGUI gui;
    private final IAuthenticator authenticator;

    @Override
    public void run() {
        User user = gui.readLoginAndPassword();
        boolean isAuthenticated = authenticator.authenticate(
                user.getLogin(),
                user.getPassword()
        );

        if (!isAuthenticated) {
            System.out.println("❌ Login failed.");
            return;
        }

        System.out.println("✅ Login successful!");
        boolean isAdmin = user.getLogin().equalsIgnoreCase("admin");

        while (true) {
            if (isAdmin) {
                gui.showAdminMenu();
            } else {
                gui.showUserMenu();
            }

            String option = gui.readMenuOption();

            switch (option) {
                case "1": // List books
                    gui.listBooks(bookRepository.getBooks());
                    break;

                case "2": // Rent book
                    try {
                        String title = gui.readBookTitle();
                        List<Book> matchingBooks = bookRepository.findByTitle(title);
                        if (matchingBooks.isEmpty()) {
                            System.out.println("⚠️ No book found with this title.");
                            break;
                        }
                        Book bookToRent = matchingBooks.get(0);
                        bookRepository.rentBook(bookToRent);
                        gui.showRentSuccessMessage(true);
                    } catch (Exception e) {
                        gui.showRentSuccessMessage(false);
                    }
                    break;

                case "3": // Return book
                    try {
                        String title = gui.readBookTitle();
                        List<Book> matchingBooks = bookRepository.findByTitle(title);
                        if (matchingBooks.isEmpty()) {
                            System.out.println("⚠️ No book found with this title.");
                            break;
                        }
                        Book bookToReturn = matchingBooks.get(0);
                        bookRepository.returnBook(bookToReturn);
                        gui.showReturnSuccessMessage(true);
                    } catch (Exception e) {
                        gui.showReturnSuccessMessage(false);
                    }
                    break;

                case "4": // Edit book (admin only)
                    if (!isAdmin) {
                        gui.showWrongOptionMessage();
                        break;
                    }
                    Book updatedBook = gui.readBookToEdit();
                    bookRepository.updateBook(updatedBook);
                    System.out.println("✅ Book updated.");
                    break;

                case "0": // Exit
                    System.out.println("👋 Exiting program...");
                    return;

                default:
                    gui.showWrongOptionMessage();
                    break;
            }
        }
    }
}
