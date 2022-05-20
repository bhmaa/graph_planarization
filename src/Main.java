import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        int[][] graph = {{0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0},
                {1, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0},
                {0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 1, 1},
                {0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1},
                {0, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}};
        Hamiltonian hamiltonian = new Hamiltonian(graph);
        hamiltonian.findHamiltonianCycle();
        int[][] renamed = hamiltonian.rename(graph);
        Intersections intersections = new Intersections(renamed);
        int[][] intersect = intersections.findIntersections();
        MaximalStableSets MaximalStableSets = new MaximalStableSets(intersect);
        MaximalStableSets.findAll();
    }
}
