
package service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import model.Book;
import model.ResearchPaper;

public class SearchService {

    private final LibraryService library;

    public SearchService(LibraryService library) {
        this.library = library;
    }

    // Case-insensitive whole-word or complete-phrase search
    private boolean matches(String text, String keyword) {
        if (text == null || keyword == null ||
                keyword.trim().isEmpty()) {
            return false;
        }

        String regex = "(?iu)(?<![\\p{L}\\p{N}_])"
                + Pattern.quote(keyword.trim())
                + "(?![\\p{L}\\p{N}_])";

        return Pattern.compile(regex).matcher(text).find();
    }

    // Search books
    public List<Book> searchBooks(String keyword) {
        List<Book> results = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }

        for (Book book : library.getBooks()) {
            if (matches(book.getTitle(), keyword)
                    || matches(book.getAuthor(), keyword)
                    || matches(book.getKeyword(), keyword)) {
                results.add(book);
            }
        }

        return results;
    }

    // Search research papers
    public List<ResearchPaper> searchPapers(String keyword) {
        List<ResearchPaper> results = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return results;
        }

        for (ResearchPaper paper : library.getPapers()) {
            if (matches(paper.getTitle(), keyword)
                    || matches(paper.getAuthor(), keyword)
                    || matches(paper.getAbstractText(), keyword)) {
                results.add(paper);
            }
        }

        return results;
    }

    // Display book results
    public void displayBookResults(String keyword) {
        List<Book> results = searchBooks(keyword);

        System.out.println("\n========== BOOK SEARCH RESULTS ==========");

        if (results.isEmpty()) {
            System.out.println("No matching books found.");
        } else {
            for (Book book : results) {
                System.out.println(book);
                System.out.println("-----------------------------------------");
            }
            System.out.println("Total books found: " + results.size());
        }
    }

    // Display research paper results
    public void displayPaperResults(String keyword) {
        List<ResearchPaper> results = searchPapers(keyword);

        System.out.println("\n========== RESEARCH PAPER SEARCH ==========");

        if (results.isEmpty()) {
            System.out.println("No matching research papers found.");
        } else {
            for (ResearchPaper paper : results) {
                System.out.println("Paper ID         : " + paper.getPaperId());
                System.out.println("Title            : " + paper.getTitle());
                System.out.println("Author           : " + paper.getAuthor());
                System.out.println("Abstract         : " + paper.getAbstractText());
                System.out.println("Publication Year : " + paper.getPublicationYear());
                System.out.println("-------------------------------------------");
            }

            System.out.println("Total papers found: " + results.size());
        }
    }
}
