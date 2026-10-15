
package algorithms;

import java.util.Arrays;

public class M2_SuffixStructures {

    // 1. Build Suffix Array
    public static int[] buildSuffixArray(String text) {
        int n = text.length();
        Integer[] suffixes = new Integer[n];

        for (int i = 0; i < n; i++) {
            suffixes[i] = i;
        }

        Arrays.sort(suffixes, (a, b) -> {
            int i = a, j = b;

            while (i < n && j < n) {
                int comparison = Character.compare(
                    text.charAt(i), text.charAt(j));

                if (comparison != 0) {
                    return comparison;
                }

                i++;
                j++;
            }

            return Integer.compare(n - a, n - b);
        });

        int[] suffixArray = new int[n];

        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i];
        }

        return suffixArray;
    }

    // 2. Kasai's Algorithm: Build LCP Array
    public static int[] buildLCP(String text, int[] sa) {
        int n = text.length();
        int[] rank = new int[n];
        int[] lcp = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        int common = 0;

        for (int i = 0; i < n; i++) {
            int r = rank[i];

            if (r == 0) {
                common = 0;
                continue;
            }

            int j = sa[r - 1];

            while (i + common < n &&
                   j + common < n &&
                   text.charAt(i + common) ==
                   text.charAt(j + common)) {
                common++;
            }

            lcp[r] = common;

            if (common > 0) {
                common--;
            }
        }

        return lcp;
    }

    // 3. Search a pattern using the Suffix Array
    public static int searchSubstring(
            String text, int[] sa, String pattern) {

        if (pattern.isEmpty()) {
            return 0;
        }

        int low = 0;
        int high = sa.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int pos = sa[mid];

            int comparison = text.substring(pos)
                                  .compareTo(pattern);

            if (comparison == 0 ||
                text.startsWith(pattern, pos)) {
                return pos;
            }

            if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    // 4. Display all suffixes
    public static void displaySuffixes(String text, int[] sa) {
        System.out.println("\nSuffix Array:");

        for (int position : sa) {
            System.out.println(position + " : "
                + text.substring(position));
        }
    }

    // Main method for testing M2
    public static void main(String[] args) {

        String text = "banana";

        int[] sa = buildSuffixArray(text);
        int[] lcp = buildLCP(text, sa);

        System.out.println("Document: " + text);
        System.out.println("Suffix Array: "
            + Arrays.toString(sa));

        System.out.println("LCP Array: "
            + Arrays.toString(lcp));

        displaySuffixes(text, sa);

        String pattern = "ana";

        System.out.println("\nSearching for: " + pattern);

        int result = searchSubstring(text, sa, pattern);

        if (result != -1) {
            System.out.println("Pattern found at index: " + result);
        } else {
            System.out.println("Pattern not found.");
        }
    }
}
