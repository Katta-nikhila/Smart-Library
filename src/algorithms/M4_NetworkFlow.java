
package algorithms;

import java.util.*;

public class M4_NetworkFlow {

    // 1. Bipartite matching: papers to eligible reviewers.
    // graph[p] contains reviewer indexes eligible for paper p.
    private static boolean assign(int paper, boolean[] visited,
                                  int[] reviewerMatch, List<List<Integer>> graph) {
        for (int reviewer : graph.get(paper)) {
            if (!visited[reviewer]) {
                visited[reviewer] = true;

                if (reviewerMatch[reviewer] == -1 ||
                    assign(reviewerMatch[reviewer], visited,
                           reviewerMatch, graph)) {
                    reviewerMatch[reviewer] = paper;
                    return true;
                }
            }
        }
        return false;
    }

    public static int maximumMatching(List<List<Integer>> graph,
                                      int reviewerCount) {
        int[] reviewerMatch = new int[reviewerCount];
        Arrays.fill(reviewerMatch, -1);

        int matched = 0;
        for (int paper = 0; paper < graph.size(); paper++) {
            if (assign(paper, new boolean[reviewerCount],
                       reviewerMatch, graph)) {
                matched++;
            }
        }

        System.out.println("Reviewer assignments:");
        for (int r = 0; r < reviewerCount; r++) {
            if (reviewerMatch[r] != -1) {
                System.out.println("Paper " + reviewerMatch[r]
                    + " -> Reviewer " + r);
            }
        }
        return matched;
    }

    // 2. Edmonds-Karp: maximum flow using BFS.
    public static int maxFlow(int[][] capacity, int source, int sink) {
        int n = capacity.length;
        int[][] residual = new int[n][n];

        for (int i = 0; i < n; i++) {
            residual[i] = capacity[i].clone();
        }

        int flow = 0;
        int[] parent = new int[n];

        while (bfs(residual, source, sink, parent)) {
            int pathFlow = Integer.MAX_VALUE;

            for (int v = sink; v != source; v = parent[v]) {
                pathFlow = Math.min(pathFlow,
                                    residual[parent[v]][v]);
            }

            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residual[u][v] -= pathFlow;
                residual[v][u] += pathFlow;
            }

            flow += pathFlow;
        }
        return flow;
    }

    private static boolean bfs(int[][] residual, int source,
                               int sink, int[] parent) {
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[residual.length];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < residual.length; v++) {
                if (!visited[v] && residual[u][v] > 0) {
                    parent[v] = u;
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return visited[sink];
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = new ArrayList<>();

        graph.add(Arrays.asList(0, 1));
        graph.add(Arrays.asList(0));
        graph.add(Arrays.asList(1, 2));

        System.out.println("Maximum paper-reviewer matching: "
            + maximumMatching(graph, 3));

        int[][] capacity = {
            {0, 3, 2, 0},
            {0, 0, 1, 2},
            {0, 0, 0, 3},
            {0, 0, 0, 0}
        };

        System.out.println("Maximum flow: "
            + maxFlow(capacity, 0, 3));
    }
}
