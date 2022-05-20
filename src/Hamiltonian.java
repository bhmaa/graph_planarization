import java.util.Arrays;
import java.util.StringJoiner;

public class Hamiltonian {
    private final int[][] graph;
    private int pathCount = 0;
    private final int[] path;
    private final int vertexes;

    public Hamiltonian(int[][] graph) {
        this.graph = new int[graph.length][graph.length];
        for (int i = 0; i < graph.length; i++) {
            System.arraycopy(graph[i], 0, this.graph[i], 0, graph.length);
        }
        vertexes = graph.length;
        path = new int[vertexes];
        Arrays.fill(path, -1);
    }

    public void findHamiltonianCycle() {
        try {
            path[0] = 0;
            pathCount = 1;
            solve(0);
            System.out.println("no hamiltonian cycle");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            display();
        }
    }

    private void solve(int vertex) throws Exception {
        if (graph[vertex][0] == 1 && pathCount == vertexes) throw new Exception("hamiltonian cycle was found");
        if (pathCount == vertexes) return;
        for (int v = 0; v < vertexes; v++) {
            if (graph[vertex][v] != 0) {
                path[pathCount++] = v;
                graph[vertex][v] = 0;
                graph[v][vertex] = 0;
                if (!isPresent(v)) {
                    solve(v);
                }
                graph[vertex][v] = 1;
                graph[v][vertex] = 1;
                path[--pathCount] = -1;
            }
        }
    }

    private boolean isPresent(int v) {
        for (int i = 0; i < pathCount - 1; i++) {
            if (path[i] == v) {
                return true;
            }
        }
        return false;
    }

    private void display() {
        StringJoiner pathJoin = new StringJoiner("->");
        for (int i = 0; i < vertexes; i++) {
            pathJoin.add("e" + (path[i] + 1));
        }
        System.out.println(pathJoin);
    }

    public int[][] rename(int[][] gr) {
        int[][] renamed = new int[vertexes][vertexes];
        System.out.println("Renamed graph:");
        System.out.print("    | ");
        for (int i = 0; i < vertexes; i++) {
            System.out.printf("%-4s", "e" + (path[i] + 1) + " ");
        }
        System.out.println();
        System.out.print("    | ");
        for (int i = 0; i < vertexes; i++) {
            System.out.printf("%-4s", (i + 1) + " ");
        }
        System.out.printf("%-4s", "p");
        System.out.println("\n----|----------------------------------------------------------");
        int sump = 0;
        for (int i = 0; i < vertexes; i++) {
            int p = 0;
            System.out.printf("%-6s", (i + 1) + " | ");
            for (int j = 0; j < vertexes; j++) {
                System.out.printf("%-4s", gr[path[i]][path[j]] + " ");
                p += gr[path[i]][path[j]];
                renamed[i][j] = gr[path[i]][path[j]];
            }
            System.out.printf("%-4s", p);
            sump += p;
            System.out.println();
        }
        System.out.println("p=" + sump);
        return renamed;
    }
}