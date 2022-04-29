import java.util.*;

public class MaximalStableSets {
    private final int[][] matrix;
    private final int size;
    private int alpha = 1;
    private List<TreeSet<Integer>> psi = new ArrayList<>();

    public MaximalStableSets(int[][] matrix) {
        this.matrix = matrix;
        this.size = matrix.length;
    }

    public void findAll() {
        for (int k = 0; k < size; k++) {
            int[] disjunction = new int[size];
            System.arraycopy(matrix[k], 0, disjunction, 0, size);
            List<Integer> myvm = new ArrayList<>();
            myvm.add(k);
            findMaximalStableSet(disjunction, myvm);
        }
        getCoverage();
    }

    private void findMaximalStableSet(int[] disjunction, List<Integer> maximalStableSets) {
        List<Integer> nulls = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (disjunction[i] == 0) nulls.add(i);
        }
        if (nulls.size() == 0) {
            TreeSet<Integer> set = new TreeSet<>(maximalStableSets);
            if (!psi.contains(set)) {
                StringJoiner sj = new StringJoiner(", ");
                set.forEach(e -> sj.add(String.valueOf(e + 1)));
                System.out.println("psi(" + alpha + ")={" + sj + "}");
                alpha++;
                psi.add(set);
            }
        } else {
            for (int i : nulls) {
                int[] disjunctionCopy = new int[size];
                List<Integer> setsCopy = new ArrayList<>(maximalStableSets);
                for (int j = 0; j < size; j++) {
                    disjunctionCopy[j] = disjunction[j] == 0 ? matrix[i][j] : 1;
                }
                setsCopy.add(i);
                findMaximalStableSet(disjunctionCopy, setsCopy);
            }
        }
    }

    private void getCoverage() {
        int m = 0;
        do {
            int[][] alphas = new int[psi.size()][psi.size()];
            int max = 0;
            Set<Integer> firstEl = null;
            int firstIndex = 0;
            Set<Integer> secondEl = null;
            int secondIndex = 0;
            for (int i = 0; i < psi.size(); i++) {
                for (int j = i + 1; j < psi.size(); j++) {
                    Set<Integer> intersections = new TreeSet<>(psi.get(i));
                    intersections.retainAll(psi.get(j));
                    int alphaIJ = psi.get(i).size() + psi.get(j).size() - intersections.size();
                    alphas[i][j] = alphaIJ;
                    alphas[j][i] = alphaIJ;
                    if (alphaIJ > max) {
                        max = alphaIJ;
                        firstEl = psi.get(i);
                        firstIndex = i;
                        secondEl = psi.get(j);
                        secondIndex = j;
                    }
                }
            }
            System.out.print("      ");
            for (int i = 0; i < psi.size(); i++) {
                System.out.printf("%-6s", "psi" + (i + 1) + " ");
            }
            System.out.println();
            for (int i = 0; i < psi.size(); i++) {
                System.out.printf("%-6s", "psi" + (i + 1) + " ");
                for (int j = 0; j < psi.size(); j++) {
                    System.out.printf("%-6s", alphas[i][j] + " ");
                    if (j == psi.size() - 1) System.out.println();
                }
            }
            System.out.print("max value of alpha = " + max + " and it gets by pair of ");
            assert firstEl != null;
            StringJoiner firstElementJoin = new StringJoiner(", ");
            firstEl.forEach(e -> firstElementJoin.add(String.valueOf(e + 1)));
            System.out.print("psi(" + (firstIndex + 1) + ")={" + firstElementJoin + "}");
            System.out.print(" and ");
            StringJoiner secondElementJoin = new StringJoiner(", ");
            secondEl.forEach(e -> secondElementJoin.add(String.valueOf(e + 1)));
            System.out.println("psi(" + (secondIndex + 1) + ")={" + secondElementJoin + "}");
            Set<Integer> busyElements = new TreeSet<>();
            TreeSet<Integer> remains = new TreeSet<>();
            busyElements.addAll(firstEl);
            busyElements.addAll(secondEl);
            List<TreeSet<Integer>> r = new ArrayList<>();
            for (TreeSet<Integer> integers : psi) {
                integers.removeAll(busyElements);
                remains.addAll(integers);
                if (!r.contains(integers) && integers.size() > 0) {
                    r.add(integers);
                }
            }
            System.out.print("unrealized edges: ");
            if (remains.size() == 0) {
                System.out.println("none");
            } else {
                StringJoiner remainsJoiner = new StringJoiner(", ");
                for (int k : remains) {
                    remainsJoiner.add(String.valueOf(k + 1));
                }
                System.out.println(remainsJoiner);
            }
            for (TreeSet<Integer> k : r) {
                StringJoiner setJoiner = new StringJoiner(", ");
                k.forEach(e -> setJoiner.add(String.valueOf(e + 1)));
                System.out.println("psi(" + (r.indexOf(k) + 1) + ")={" + setJoiner + "}");
            }
            psi = r;
            m++;
        } while (psi.size() > 1);
        if (psi.size() == 1) {
            System.out.println("remained one set:");
            for (TreeSet<Integer> k : psi) {
                StringJoiner remainsJoiner = new StringJoiner(", ");
                k.forEach(e -> remainsJoiner.add(String.valueOf(e + 1)));
                System.out.println(remainsJoiner);
            }
        }
        System.out.println("graph thickness m=" + m);
    }
}
