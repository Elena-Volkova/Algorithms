package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUFPercolation;
    private WeightedQuickUnionUF weightedQuickUnionUFFull;
    private boolean[] grid;
    private int n;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        weightedQuickUnionUFPercolation = new WeightedQuickUnionUF(n * n + 2);
        weightedQuickUnionUFFull = new WeightedQuickUnionUF(n * n + 1);
        grid = new boolean[n * n + 2];
        this.n = n;

        for (int i = 0; i < n * n; i++) {
            grid[i] = false;
        }

        //Initialize virtual sites
        grid[n * n] = true;
        grid[n * n + 1] = true;

        for (int i = 0; i < n; i++) {
            weightedQuickUnionUFPercolation.union(n * n, xyTo1D(0, i));
            weightedQuickUnionUFFull.union(n * n, xyTo1D(0, i));
            weightedQuickUnionUFPercolation.union(n * n + 1, xyTo1D(n - 1, i));
        }
    }

    public void open(int i, int j) {
        if (!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        int indexCurrent = xyTo1D(i - 1, j - 1);
        grid[indexCurrent] = true;

        int indexLeft = xyTo1D(i - 1, j - 2);
        connect(i, j - 1, indexCurrent, indexLeft);
        int indexUp = xyTo1D(i - 2, j - 1);
        connect(i - 1, j, indexCurrent, indexUp);
        int indexRight = xyTo1D(i - 1, j);
        connect(i, j + 1, indexCurrent, indexRight);
        int indexDown = xyTo1D(i, j - 1);
        connect(i + 1, j, indexCurrent, indexDown);
    }

    private void connect(int i, int j, int indexCurrent, int indexToConnect) {
        if (validate(i, j) && isOpen(i, j)) {
            weightedQuickUnionUFPercolation.union(indexCurrent, indexToConnect);
            weightedQuickUnionUFFull.union(indexCurrent, indexToConnect);
        }
    }

    public boolean isOpen(int i, int j) {
        if (!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        return grid[xyTo1D(i - 1, j - 1)];
    }

    public boolean isFull(int i, int j) {
        if (!validate(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        return isOpen(i, j) && weightedQuickUnionUFFull.connected(n * n, xyTo1D(i - 1, j - 1));

    }

    public boolean percolates() {
        if (n == 1) {
            return isOpen(n, n);
        } else {
            return weightedQuickUnionUFPercolation.connected(n * n, n * n + 1);
        }
    }

    private boolean validate(int i, int j) {
        return i > 0 && i <= n && j > 0 && j <= n;
    }

    private int xyTo1D(int x, int y) {
        return x * n + y;
    }
}

