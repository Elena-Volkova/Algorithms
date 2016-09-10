package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int[] grid;
    private int n;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
        grid = new int[n * n];

        for (int i = 0; i < n * n; i++) {
            grid[i] = -1;
        }
    }

    public void open(int i, int j) {
        if (!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        if (!isOpen(i, j)) {
            boolean isFull = i == 1;

            int indexCurrent = xyTo1D(i - 1, j - 1);
            int indexLeft = xyTo1D(i - 1, j - 2);
            if (validate(i, j - 1) && isOpen(i, j - 1)) {
                if (isFull(i, j - 1)) {
                    isFull = true;
                }
                weightedQuickUnionUF.union(indexCurrent, indexLeft);
            }
            int indexUp = xyTo1D(i - 2, j - 1);
            if (validate(i - 1, j) && isOpen(i - 1, j)) {
                if (isFull(i - 1, j)) {
                    isFull = true;
                }
                weightedQuickUnionUF.union(indexCurrent, indexUp);
            }
            int indexRight = xyTo1D(i - 1, j);
            if (validate(i, j + 1) && isOpen(i, j + 1)) {
                if (isFull(i, j + 1)) {
                    isFull = true;
                }
                weightedQuickUnionUF.union(indexCurrent, indexRight);
            }
            int indexDown = xyTo1D(i, j - 1);
            if (validate(i + 1, j) && isOpen(i + 1, j)) {
                if (isFull(i + 1, j)) {
                    isFull = true;
                }
                weightedQuickUnionUF.union(indexCurrent, indexDown);
            }

            grid[indexCurrent] = 0;

            if (isFull) {
                grid[weightedQuickUnionUF.find(indexCurrent)] = 1;
            }

        }
    }

    public boolean isOpen(int i, int j) {
        if (!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        return grid[xyTo1D(i - 1, j - 1)] != -1;
    }

    public boolean isFull(int i, int j) {
        if (!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        return grid[weightedQuickUnionUF.find(xyTo1D(i - 1, j - 1))] == 1;
    }

    public boolean percolates() {
        for (int i = 0; i < n; i++) {
            if (isOpen(n, i + 1) && isFull(n, i + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean validate(int i, int j) {
        return i > 0 && i <= n && j > 0 && j <= n;
    }

    private int xyTo1D(int x, int y) {
        return x * n + y;
    }
}

