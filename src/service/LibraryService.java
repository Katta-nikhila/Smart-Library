
package service;

import java.util.ArrayList;
import java.util.List;

import model.Book;
import model.ResearchPaper;

public class LibraryService {

    private List<Book> books = new ArrayList<>();
    private List<ResearchPaper> papers = new ArrayList<>();

    public LibraryService() {

        // Sample books
        books.add(new Book(1, "Java Programming",
                "James Gosling", "programming"));

        books.add(new Book(2, "Data Structures and Algorithms",
                "Mark Allen Weiss", "algorithms"));

        books.add(new Book(3, "Machine Learning Basics",
                "Tom Mitchell", "machine learning"));

        books.add(new Book(4, "Database Management Systems",
                "Raghu Ramakrishnan", "database"));

        // Sample research papers
        papers.add(new ResearchPaper(
                101,
                "Machine Learning Applications",
                "John Smith",
                "Machine learning helps analyze large datasets.",
                2024
        ));

        papers.add(new ResearchPaper(
                102,
                "Efficient String Searching",
                "Alice Brown",
                "String algorithms improve text search performance.",
                2023
        ));
    }

    // Display all books neatly
    public void displayBooks() {

        System.out.println("\n========== AVAILABLE BOOKS ==========");

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
            System.out.println("-------------------------------------");
        }
    }

    // Display all research papers
    public void displayResearchPapers() {

        System.out.println("\n======= AVAILABLE RESEARCH PAPERS =======");

        if (papers.isEmpty()) {
            System.out.println("No research papers available.");
            return;
        }

        for (ResearchPaper paper : papers) {
            System.out.println(paper);
            System.out.println("-----------------------------------------");
        }
    }

    // Return a copy of the book list
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    // Return a copy of the research paper list
    public List<ResearchPaper> getPapers() {
        return new ArrayList<>(papers);
    }

    // Add a new book
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    // Add a new research paper
    public void addResearchPaper(ResearchPaper paper) {
        papers.add(paper);
        System.out.println("Research paper added successfully!");
    }
}
