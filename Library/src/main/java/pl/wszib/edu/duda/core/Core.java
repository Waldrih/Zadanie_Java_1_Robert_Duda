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
            System.out.println("Login failed.");
            return;
        }

        System.out.println("Login successful!");
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
                        List<Book> matchingBooks = searchBooksByTitleOrAuthor();
                        if (matchingBooks.isEmpty()) {
                            System.out.println("No matching book found.");
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
                        List<Book> matchingBooks = searchBooksByTitleOrAuthor();
                        if (matchingBooks.isEmpty()) {
                            System.out.println("No matching book found.");
                            break;
                        }
                        Book bookToReturn = matchingBooks.get(0);
                        bookRepository.returnBook(bookToReturn);
                        gui.showReturnSuccessMessage(true);
                    } catch (Exception e) {
                        gui.showReturnSuccessMessage(false);
                    }
                    break;

                case "4": // Edit book (admin)
                    if (!isAdmin) {
                        gui.showWrongOptionMessage();
                        break;
                    }

                    try {
                        List<Book> matchingBooks = searchBooksByTitleOrAuthor();
                        if (matchingBooks.isEmpty()) {
                            System.out.println("No book found to edit.");
                            break;
                        }

                        Book oldBook = matchingBooks.get(0);
                        Book newBook = gui.readBookToEdit();
                        bookRepository.removeBook(oldBook);
                        bookRepository.addBook(newBook);

                        System.out.println("Book updated.");
                    } catch (Exception e) {
                        System.out.println("Error updating book.");
                    }
                    break;

                case "5": // Add book (admin)
                    if (!isAdmin) {
                        gui.showWrongOptionMessage();
                        break;
                    }

                    Book newBook = gui.readNewBook();
                    bookRepository.addBook(newBook);
                    System.out.println("Book added.");
                    break;

                case "6": // Remove book (admin)
                    if (!isAdmin) {
                        gui.showWrongOptionMessage();
                        break;
                    }

                    try {
                        List<Book> matchingBooks = searchBooksByTitleOrAuthor();
                        if (matchingBooks.isEmpty()) {
                            System.out.println("No book found to remove.");
                            break;
                        }

                        Book bookToRemove = matchingBooks.get(0);
                        bookRepository.removeBook(bookToRemove);
                        System.out.println("Book removed.");
                    } catch (Exception e) {
                        System.out.println("Error removing book.");
                    }
                    break;

                case "0": // Exit
                    System.out.println("Exiting program...");
                    return;

                default:
                    gui.showWrongOptionMessage();
                    break;
            }
        }
    }

    private List<Book> searchBooksByTitleOrAuthor() {
        String mode = gui.askSearchMode();
        if (mode.equals("1")) {
            String title = gui.readBookTitle();
            return bookRepository.findByTitle(title);
        } else if (mode.equals("2")) {
            String author = gui.readBookAuthor();
            return bookRepository.findByAuthor(author);
        } else {
            gui.showWrongOptionMessage();
            return List.of();
        }
    }
}
