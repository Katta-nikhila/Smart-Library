
package algorithms;

public class M3_DynamicProgramming {

    // 1. Levenshtein Edit Distance
    public static int editDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= b.length(); j++) dp[0][j] = j;

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cost
                );
            }
        }
        return dp[a.length()][b.length()];
    }

    // 2. Damerau-Levenshtein distance (optimal string alignment variant)
    public static int damerauDistance(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                    Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                    dp[i - 1][j - 1] + cost
                );

                if (i > 1 && j > 1
                        && a.charAt(i - 1) == b.charAt(j - 2)
                        && a.charAt(i - 2) == b.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 2] + 1);
                }
            }
        }
        return dp[m][n];
    }

    // 3. Matrix-Chain Multiplication
    // p contains matrix dimensions: A1=p[0]xp[1], A2=p[1]xp[2], etc.
    public static long matrixChain(int[] p) {
        int n = p.length - 1;
        if (n <= 1) return 0;

        long[][] dp = new long[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Long.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    long cost = dp[i][k] + dp[k + 1][j]
                        + (long) p[i] * p[k + 1] * p[j + 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[0][n - 1];
    }

    // 4. Bitmask DP: maximum value within capacity.
    // Suitable for small item counts (roughly 20 or fewer).
    public static int bitmaskKnapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        if (n >= 31) throw new IllegalArgumentException("Too many items");

        int answer = 0;
        for (int mask = 0; mask < (1 << n); mask++) {
            int weight = 0, value = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    weight += weights[i];
                    value += values[i];
                }
            }

            if (weight <= capacity) answer = Math.max(answer, value);
        }
        return answer;
    }

    // 5. Optimal BST: minimum search cost for successful-search frequencies.
    public static long optimalBST(int[] freq) {
        int n = freq.length;
        if (n == 0) return 0;

        long[][] dp = new long[n][n];
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + freq[i];
            dp[i][i] = freq[i];
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Long.MAX_VALUE;
                long sum = prefix[j + 1] - prefix[i];

                for (int r = i; r <= j; r++) {
                    long left = r > i ? dp[i][r - 1] : 0;
                    long right = r < j ? dp[r + 1][j] : 0;
                    dp[i][j] = Math.min(dp[i][j], left + right + sum);
                }
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println("Edit distance: " +
            editDistance("jav", "java"));

        System.out.println("Damerau distance: " +
            damerauDistance("form", "from"));

        System.out.println("Matrix-chain cost: " +
            matrixChain(new int[]{10, 20, 30, 40}));

        System.out.println("Best resource value: " +
            bitmaskKnapsack(new int[]{2, 3, 4},
                            new int[]{4, 5, 7}, 5));

        System.out.println("Optimal BST cost: " +
            optimalBST(new int[]{3, 1, 2}));
    }
}
