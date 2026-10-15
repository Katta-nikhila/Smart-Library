
package ui;

import java.util.Scanner;

import model.Book;
import model.ResearchPaper;
import service.LibraryService;
import service.SearchService;

import algorithms.M1_StringAlgorithms;
import algorithms.M2_SuffixStructures;
import algorithms.M3_DynamicProgramming;
import algorithms.M4_NetworkFlow;
import algorithms.M5_NPCompleteness;
import algorithms.M6_RandomizedParallel;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final LibraryService library = new LibraryService();
    private static final SearchService search = new SearchService(library);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n================================================");
            System.out.println("             SMART LIBRARY");
            System.out.println("       Intelligent Digital Library Platform");
            System.out.println("================================================");
            System.out.println("  LIBRARY MANAGEMENT");
            System.out.println("  1. Display Books");
            System.out.println("  2. Display Research Papers");
            System.out.println("  3. Search Books");
            System.out.println("  4. Search Research Papers");
            System.out.println("  5. Add Book");
            System.out.println("  6. Add Research Paper");
            System.out.println("------------------------------------------------");
            System.out.println("  DSA MODULES");
            System.out.println("  M1 - String Algorithms");
            System.out.println("  M2 - Suffix Structures");
            System.out.println("  M3 - Dynamic Programming");
            System.out.println("  M4 - Network Flow");
            System.out.println("  M5 - NP-Completeness");
            System.out.println("  M6 - Randomized and Parallel Algorithms");
            System.out.println("------------------------------------------------");
            System.out.println("  0. Exit");
            System.out.println("================================================");

            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;

                case 2:
                    library.displayResearchPapers();
                    break;

                case 3:
                    search.displayBookResults(
                            readText("Enter book keyword: "));
                    break;

                case 4:
                    search.displayPaperResults(
                            readText("Enter research paper keyword: "));
                    break;

                case 5:
                    addBook();
                    break;

                case 6:
                    addResearchPaper();
                    break;

                case 7:
                    M1_StringAlgorithms.main(new String[0]);
                    break;

                case 8:
                    M2_SuffixStructures.main(new String[0]);
                    break;

                case 9:
                    M3_DynamicProgramming.main(new String[0]);
                    break;

                case 10:
                    M4_NetworkFlow.main(new String[0]);
                    break;

                case 11:
                    M5_NPCompleteness.main(new String[0]);
                    break;

                case 12:
                    M6_RandomizedParallel.main(new String[0]);
                    break;

                case 0:
                    System.out.println(
                            "\nThank you for using Smart Library!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please select a listed option.");
            }

            if (choice != 0) {
                System.out.println("\nPress Enter to return to the main menu...");
                sc.nextLine();
            }

        } while (choice != 0);

        sc.close();
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a whole number.");
            }
        }
    }

    private static void addBook() {
        int id = readInt("Enter book ID: ");
        String title = readText("Enter book title: ");
        String author = readText("Enter author name: ");
        String keyword = readText("Enter book keyword: ");

        library.addBook(new Book(id, title, author, keyword));
    }

    private static void addResearchPaper() {
        int id = readInt("Enter paper ID: ");
        String title = readText("Enter paper title: ");
        String author = readText("Enter author name: ");
        String abstractText = readText("Enter abstract: ");
        int year = readInt("Enter publication year: ");

        library.addResearchPaper(
                new ResearchPaper(id, title, author, abstractText, year));
    }
}
