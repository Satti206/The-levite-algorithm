import java.util.Comparator;
import java.util.PriorityQueue;

class Levit {
    private int[][] graph;
    public static int iterations;

    public Levit(int[][] graph) {
        this.graph = graph;
    }

    public void shortestPath(int source) {
        int n = graph.length;
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];
        PriorityQueue<Node> pq = new PriorityQueue<>(n    , new NodeComparator());
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[source] = 0;
        pq.add(new Node(source, 0));
        iterations = 0;
        while (!pq.isEmpty()) {
            iterations++;
            Node curr = pq.poll();
            int u = curr.vertex;
            if (visited[u]) {
                continue;
            }
            visited[u] = true;
            for (int v = 0; v < n; v++) {
                int w = graph[u][v];
                if (w > 0 && !visited[v] && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.add(new Node(v, dist[v]));
                }
            }
        }
    }

    private static class Node {
        public int vertex;
        public int distance;

        public Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    private static class NodeComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            return n1.distance - n2.distance;
        }
    }
}
