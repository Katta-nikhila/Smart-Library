
package ui;

import java.util.List;
import java.util.Scanner;

import model.Book;
import model.ResearchPaper;
import service.LibraryService;
import service.SearchService;

public class LibraryMenu {

    private static Scanner sc = new Scanner(System.in);

    private static LibraryService library = new LibraryService();
    private static SearchService search = new SearchService(library);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== SMART LIBRARY DSA SYSTEM =====");
            System.out.println("1. Display Books");
            System.out.println("2. Display Research Papers");
            System.out.println("3. Search Books");
            System.out.println("4. Search Research Papers");
            System.out.println("5. Add Book");
            System.out.println("6. Add Research Paper");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
                System.out.print("Enter your choice: ");
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    library.displayBooks();
                    break;

                case 2:
                    library.displayResearchPapers();
                    break;

                case 3:
                    System.out.print("Enter book keyword: ");
                    String bookKeyword = sc.nextLine();
                    search.displayBookResults(bookKeyword);
                    break;

                case 4:
                    System.out.print("Enter research paper keyword: ");
                    String paperKeyword = sc.nextLine();
                    search.displayPaperResults(paperKeyword);
                    break;

                case 5:
                    addBook();
                    break;

                case 6:
                    addResearchPaper();
                    break;

                case 0:
                    System.out.println("Exiting Smart Library. Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 0);

        sc.close();
    }

    private static void addBook() {
        System.out.print("Enter book ID: ");
        int id = readInt();

        System.out.print("Enter book title: ");
        String title = sc.nextLine();

        System.out.print("Enter author name: ");
        String author = sc.nextLine();

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        Book book = new Book(id, title, author, keyword);
        library.addBook(book);
    }

    private static void addResearchPaper() {
        System.out.print("Enter paper ID: ");
        int id = readInt();

        System.out.print("Enter paper title: ");
        String title = sc.nextLine();

        System.out.print("Enter author name: ");
        String author = sc.nextLine();

        System.out.print("Enter abstract: ");
        String abstractText = sc.nextLine();

        System.out.print("Enter publication year: ");
        int year = readInt();

        ResearchPaper paper =
                new ResearchPaper(id, title, author, abstractText, year);

        library.addResearchPaper(paper);
    }

    private static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a valid number: ");
            sc.next();
        }

        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }
}
