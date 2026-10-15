
package algorithms;

import java.util.Scanner;

public class M1_StringAlgorithms {

    // KMP Search Algorithm
    public static int searchKMP(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return 0;
        }

        int[] lps = new int[m];
        int j = 0;

        // Build LPS array
        for (int i = 1; i < m; ) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
                lps[i] = j + 1;
                i++;
                j++;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                lps[i] = 0;
                i++;
            }
        }

        // Search pattern in text
        int i = 0;
        j = 0;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;

                if (j == m) {
                    return i - j;
                }
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }

        return -1;
    }

    // Z Algorithm
    public static int[] calculateZ(String text) {

        int n = text.length();
        int[] z = new int[n];

        int left = 0;
        int right = 0;

        for (int i = 1; i < n; i++) {

            if (i <= right) {
                z[i] = Math.min(right - i + 1, z[i - left]);
            }

            while (i + z[i] < n &&
                   text.charAt(z[i]) == text.charAt(i + z[i])) {
                z[i]++;
            }

            if (i + z[i] - 1 > right) {
                left = i;
                right = i + z[i] - 1;
            }
        }

        return z;
    }

    // Rabin-Karp Search Algorithm
    public static int searchRabinKarp(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        if (m == 0) {
            return 0;
        }

        if (m > n) {
            return -1;
        }

        int base = 256;
        int prime = 101;

        int patternHash = 0;
        int textHash = 0;
        int highestPower = 1;

        for (int i = 0; i < m - 1; i++) {
            highestPower = (highestPower * base) % prime;
        }

        for (int i = 0; i < m; i++) {
            patternHash = (base * patternHash
                    + pattern.charAt(i)) % prime;

            textHash = (base * textHash
                    + text.charAt(i)) % prime;
        }

        for (int i = 0; i <= n - m; i++) {

            if (patternHash == textHash &&
                text.regionMatches(i, pattern, 0, m)) {
                return i;
            }

            if (i < n - m) {
                textHash = (base * (textHash
                        - text.charAt(i) * highestPower)
                        + text.charAt(i + m)) % prime;

                if (textHash < 0) {
                    textHash += prime;
                }
            }
        }

        return -1;
    }

    // Display Z Array
    public static void displayZArray(String text) {

        int[] z = calculateZ(text);

        System.out.println("Z Array:");

        for (int value : z) {
            System.out.print(value + " ");
        }

        System.out.println();
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String text = "machine learning helps search datasets";

        System.out.println("\n===== STRING ALGORITHMS =====");
        System.out.println("Text: " + text);

        System.out.print("Enter a word to search: ");
        String word = sc.nextLine().trim();

        if (word.isEmpty()) {
            System.out.println("Please enter a valid word.");
        } else {
            int result = searchKMP(text, word);

            if (result != -1) {
                System.out.println("Word found at index: " + result);
            } else {
                System.out.println("Word not found.");
            }
        }

        System.out.println("\nRabin-Karp Search");
        System.out.print("Enter another word to search: ");
        String word2 = sc.nextLine().trim();

        if (word2.isEmpty()) {
            System.out.println("Please enter a valid word.");
        } else {
            int result2 = searchRabinKarp(text, word2);

            if (result2 != -1) {
                System.out.println("Word found at index: " + result2);
            } else {
                System.out.println("Word not found.");
            }
        }

        System.out.println("\nZ Algorithm");
        System.out.print("Enter a string for Z Array: ");
        String zText = sc.nextLine();

        displayZArray(zText);

    }
}
