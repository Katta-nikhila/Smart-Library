
package model;

public class Book {

    private int id;
    private String title;
    private String author;
    private String keyword;

    public Book(int id, String title, String author, String keyword) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.keyword = keyword;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public String toString() {
        return "Book ID : " + id + "\n"
             + "Title   : " + title + "\n"
             + "Author  : " + author + "\n"
             + "Keyword : " + keyword;
    }
}
