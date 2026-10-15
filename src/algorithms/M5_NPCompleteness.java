
package algorithms;

import java.util.*;

public class M5_NPCompleteness {

    // 1. Vertex Cover 2-approximation.
    // Each selected edge contributes both endpoints.
    public static Set<Integer> vertexCoverApprox(
            int vertices, int[][] edges) {

        boolean[] covered = new boolean[vertices];
        Set<Integer> cover = new LinkedHashSet<>();

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];

            if (!covered[u] && !covered[v]) {
                cover.add(u);
                cover.add(v);
                covered[u] = true;
                covered[v] = true;
            }
        }
        return cover;
    }

    // 2. Brute-force CLIQUE solver for small graphs.
    // adjacency[u][v] is true if an edge exists.
    public static List<Integer> findClique(
            boolean[][] adjacency, int k) {

        int n = adjacency.length;
        if (k < 0 || k > n || n >= 31) {
            return Collections.emptyList();
        }

        for (int mask = 0; mask < (1 << n); mask++) {
            if (Integer.bitCount(mask) != k) continue;

            List<Integer> nodes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) nodes.add(i);
            }

            boolean clique = true;
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = i + 1; j < nodes.size(); j++) {
                    if (!adjacency[nodes.get(i)][nodes.get(j)]) {
                        clique = false;
                    }
                }
            }

            if (clique) return nodes;
        }
        return Collections.emptyList();
    }

    // 3. Check whether a given assignment satisfies a 3-SAT formula.
    // Each literal is a signed variable: +1 means x1, -1 means NOT x1.
    // Each clause must contain three literals.
    public static boolean satisfies3SAT(int[][] clauses,
                                        boolean[] assignment) {
        for (int[] clause : clauses) {
            if (clause.length != 3) {
                throw new IllegalArgumentException(
                    "Each clause must have 3 literals");
            }

            boolean clauseTrue = false;
            for (int literal : clause) {
                int index = Math.abs(literal) - 1;
                if (index < 0 || index >= assignment.length) {
                    throw new IllegalArgumentException(
                        "Invalid variable number");
                }

                boolean value = assignment[index];
                if (literal < 0) value = !value;
                if (value) clauseTrue = true;
            }

            if (!clauseTrue) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] edges = {
            {0, 1}, {1, 2}, {2, 3}
        };

        System.out.println("Approximate Vertex Cover: "
            + vertexCoverApprox(4, edges));

        boolean[][] graph = {
            {false, true,  true},
            {true,  false, true},
            {true,  true,  false}
        };

        System.out.println("Clique of size 3: "
            + findClique(graph, 3));

        int[][] clauses = {
            {1, -2, 3},
            {-1, 2, 3}
        };
        boolean[] assignment = {true, false, true};

        System.out.println("3-SAT assignment satisfies formula: "
            + satisfies3SAT(clauses, assignment));
    }
}
