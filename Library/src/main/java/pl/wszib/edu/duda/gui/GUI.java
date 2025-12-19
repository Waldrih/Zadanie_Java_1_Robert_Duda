package pl.wszib.edu.duda.gui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wszib.edu.duda.model.Book;
import pl.wszib.edu.duda.model.User;

import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class GUI implements IGUI {

    private final Scanner scanner;

    // ===== MENUS =====

    @Override
    public void showUserMenu() {
        System.out.println("1. List books");
        System.out.println("2. Rent book");
        System.out.println("3. Return book");
        System.out.println("0. Exit");
    }

    @Override
    public void showAdminMenu() {
        showUserMenu();
        System.out.println("4. Edit book");
    }

    @Override
    public String readMenuOption() {
        System.out.print("Choose option: ");
        return scanner.nextLine();
    }

    // ===== BOOK INPUT =====

    @Override
    public String readBookTitle() {
        System.out.print("Enter book title: ");
        return scanner.nextLine();
    }

    @Override
    public Book readBookToEdit() {
        System.out.print("Enter NEW title: ");
        String title = scanner.nextLine();

        System.out.print("Enter NEW author: ");
        String author = scanner.nextLine();

        return new Book(title, author);
    }

    // ===== OUTPUT =====

    @Override
    public void listBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Override
    public void showRentSuccessMessage(boolean success) {
        System.out.println(success
                ? "Book rented successfully"
                : "Cannot rent book");
    }

    @Override
    public void showReturnSuccessMessage(boolean success) {
        System.out.println(success
                ? "Book returned successfully"
                : "Cannot return book");
    }

    @Override
    public void showWrongOptionMessage() {
        System.out.println("Wrong option. Please try again.");
    }

    // ===== LOGIN =====

    @Override
    public User readLoginAndPassword() {
        System.out.print("Login: ");
        String login = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        return new User(login, password);
    }
}
