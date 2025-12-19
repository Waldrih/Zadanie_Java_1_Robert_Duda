package pl.wszib.edu.duda.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this(title, author, true);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getTitle())
                .append(" ")
                .append(this.getAuthor())
                .append(" ")
                .append(this.isAvailable() ? "AVAILABLE" : "RENTED")
                .toString();
    }
}
