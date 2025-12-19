package pl.wszib.edu.duda.model;

public class BookBuilder {
    private String title;
    private String author;
    private boolean available;

    public BookBuilder title(String title){
        this.title = title;
        return this;
    }

    public BookBuilder author(String author){
        this.author = author;
        return this;
    }

    public BookBuilder available(boolean available){
        this.available = available;
        return this;
    }

    public Book build() {return new Book(title,author, available);}
}
