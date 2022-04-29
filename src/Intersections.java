import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Intersections {
    private final int[][] graph;
    private final int vertexes;
    private final List<String> edges = new ArrayList<>();
    private final Vector<Vector<Integer>> intersections = new Vector<>();


    public Intersections(int[][] graph) {
        this.graph = graph;
        vertexes = graph.length;
    }

    public int[][] findIntersections() {
        for (int i = 1; i < vertexes; i++) {
            for (int r = i + 2; r < vertexes; r++) {
                if (graph[i][r] == 1) {
                    String edgeCurr = "u" + (i + 1) + "," + (r + 1);
                    for (int k = 0; k < i; k++) {
                        for (int j = k + 2; j < r; j++) {
                            if (graph[k][j] == 1 && j > i) {
                                String edgePrev = "u" + (k + 1) + "," + (j + 1);
                                if (!edges.contains(edgePrev)) {
                                    edges.add(edgePrev);
                                    intersections.add(new Vector<>());
                                }
                                if (!edges.contains(edgeCurr)) {
                                    edges.add(edgeCurr);
                                    intersections.add(new Vector<>());
                                }
                                int prev = edges.indexOf(edgePrev);
                                int curr = edges.indexOf(edgeCurr);
                                if (intersections.get(prev).size() <= curr) {
                                    for (int t = intersections.get(prev).size()-1; t <= curr; t++) {
                                        intersections.get(prev).add(0);
                                    }
                                }
                                if (intersections.get(curr).size() <= curr) {
                                    for (int t = intersections.get(curr).size()-1; t <= curr; t++) {
                                        intersections.get(curr).add(0);
                                    }
                                }
                                intersections.get(prev).set(curr, 1);
                                intersections.get(curr).set(prev, 1);
                            }
                        }
                    }
                }
            }
        }
        correct();
        display();
        int[][] inters = new int[edges.size()][edges.size()];
        for (int i = 0; i < edges.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                inters[i][j] = intersections.get(i).get(j);
            }
        }
        return inters;
    }

    private void correct() {
        for (int i = 0; i < edges.size(); i++) {
            if (intersections.get(i).size() < edges.size()) {
                for (int j = intersections.get(i).size(); j < edges.size(); j++) {
                    intersections.get(i).add(0);
                }
            }
        }
        for (int i = 0; i < edges.size(); i++) {
            intersections.get(i).set(i, 1);
        }
    }

    private void display() {
        System.out.println("This intersections were found: ");
        System.out.print("      ");
        for (int i = 0; i < edges.size(); i++) {
            System.out.printf("%-7s", (i + 1) + " ");
        }
        System.out.println();
        System.out.print("      ");
        for (String i : edges) {
            System.out.printf("%-7s", i + " ");
        }
        System.out.println();
        for (int i = 0; i < edges.size(); i++) {
            for (int j = 0; j < edges.size(); j++) {
                if (j == 0) System.out.printf("%-7s", edges.get(i) + " ");
                System.out.printf("%-7s", intersections.get(i).get(j) + " ");
                if (j == edges.size() - 1) System.out.println();
            }
        }
    }
}
