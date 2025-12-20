package pl.wszib.edu.duda.gui;
//nie jestem pewny jak poprawnie rozdzielać importy od siebie żeby to było przejrzyste
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

    @Override
    public void showUserMenu() {
        System.out.println("\nLIBRARY - USER MENU");
        System.out.println("1. List books");
        System.out.println("2. Rent book");
        System.out.println("3. Return book");
        System.out.println("0. Exit");
    }

    @Override
    public void showAdminMenu() {
        System.out.println("\nLIBRARY - ADMIN MENU");
        System.out.println("1. List books");
        System.out.println("2. Rent book");
        System.out.println("3. Return book");
        System.out.println("4. Edit book");
        System.out.println("5. Add book");
        System.out.println("6. Remove book");
        System.out.println("0. Exit");
    }

    @Override
    public String readMenuOption() {
        System.out.print("Choose option: ");
        return scanner.nextLine();
    }

    @Override
    public String askSearchMode() {
        System.out.println("Search by:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        System.out.print("Your choice: ");
        return scanner.nextLine();
    }

    @Override
    public String readBookTitle() {
        System.out.print("Enter book title: ");
        return scanner.nextLine();
    }

    @Override
    public String readBookAuthor() {
        System.out.print("Enter author: ");
        return scanner.nextLine();
    }

    @Override
    public void listBooks(List<Book> books) {
        System.out.println("\nBook List:");
        for (Book book : books) {
            System.out.println(" - " + book);
        }
    }

    @Override
    public void showRentSuccessMessage(boolean success) {
        System.out.println(success
                ? "Book rented successfully!"
                : "Could not rent the book.");
    }

    @Override
    public void showReturnSuccessMessage(boolean success) {
        System.out.println(success
                ? "Book returned successfully!"
                : "Could not return the book.");
    }

    @Override
    public void showWrongOptionMessage() {
        System.out.println("Invalid option. Try again.");
    }

    @Override
    public User readLoginAndPassword() {
        System.out.print("Login: ");
        String login = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        return new User(login, password);
    }

    @Override
    public Book readNewBook() {
        System.out.println("\nADD NEW BOOK");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        return new Book(title, author);
    }

    @Override
    public Book readBookToEdit() {
        System.out.println("\nEDIT BOOK - enter new data:");
        System.out.print("New title: ");
        String title = scanner.nextLine();
        System.out.print("New author: ");
        String author = scanner.nextLine();
        return new Book(title, author);
    }
}
