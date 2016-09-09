package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[][] grid;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
        grid = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
    }

    public void open(int i, int j) {
        if (!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        grid[i - 1][j - 1] = true;

        int indexCurrent = xyTo1D(i - 1, j - 1);
        int indexLeft = xyTo1D(i - 1, j - 2);
        if (validate(i, j - 1) && isOpen(i, j - 1) && !weightedQuickUnionUF.connected(indexCurrent, indexLeft)) {
            weightedQuickUnionUF.union(indexCurrent, indexLeft);
        }
        int indexUp = xyTo1D(i - 2, j - 1);
        if (validate(i - 1, j) && isOpen(i - 1, j) && !weightedQuickUnionUF.connected(indexCurrent, indexUp)) {
            weightedQuickUnionUF.union(indexCurrent, indexUp);
        }
        int indexRight = xyTo1D(i - 1, j);
        if (validate(i, j + 1) && isOpen(i, j + 1) && !weightedQuickUnionUF.connected(indexCurrent, indexRight)) {
            weightedQuickUnionUF.union(indexCurrent, indexRight);
        }
        int indexDown = xyTo1D(i, j - 1);
        if (validate(i + 1, j) && isOpen(i + 1, j) && !weightedQuickUnionUF.connected(indexCurrent, indexDown)) {
            weightedQuickUnionUF.union(indexCurrent, indexDown);
        }
    }

    public boolean isOpen(int i, int j) {
        if (!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        if (!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        if (isOpen(i, j)) {
            for (int k = 0; k < grid.length; k++) {
                if (weightedQuickUnionUF.connected(k, xyTo1D(i - 1, j - 1))) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean percolates() {
        for (int i = 0; i < grid.length; i++) {
            if (isFull(grid.length, i + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean validate(int i, int j) {
        return i > 0 && i <= grid.length && j > 0 && j <= grid.length;
    }

    private int xyTo1D(int x, int y) {
        return x * grid.length + y;
    }
}

