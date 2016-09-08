package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[][] grid;
    private int gridSize;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }

        gridSize = n;
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
        grid = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
    }

    public void open(int i, int j) {
        if (!areCorrectIndices(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        int gridI = i - 1;
        int gridJ = j - 1;

        grid[gridI][gridJ] = true;

        int indexCurrent = gridI * gridSize + gridJ;
        int indexLeft = gridI * gridSize + (gridJ - 1);
        if (areCorrectIndices(i, j - 1) && isOpen(i, j - 1) && !weightedQuickUnionUF.connected(indexCurrent, indexLeft)) {
            weightedQuickUnionUF.union(indexCurrent, indexLeft);
        }
        int indexUp = (gridI - 1) * gridSize + gridJ;
        if (areCorrectIndices(i - 1, j) && isOpen(i - 1, j) && !weightedQuickUnionUF.connected(indexCurrent, indexUp)) {
            weightedQuickUnionUF.union(indexCurrent, indexUp);
        }
        int indexRight = gridI * gridSize + gridJ + 1;
        if (areCorrectIndices(i, j + 1) && isOpen(i, j + 1) && !weightedQuickUnionUF.connected(indexCurrent, indexRight)) {
            weightedQuickUnionUF.union(indexCurrent, indexRight);
        }
        int indexDown = (gridI + 1) * gridSize + gridJ;
        if (areCorrectIndices(i + 1, j) && isOpen(i + 1, j) && !weightedQuickUnionUF.connected(indexCurrent, indexDown)) {
            weightedQuickUnionUF.union(indexCurrent, indexDown);
        }
    }

    public boolean isOpen(int i, int j) {
        if (!areCorrectIndices(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        int gridI = i - 1;
        int gridJ = j - 1;

        return grid[gridI][gridJ];
    }

    public boolean isFull(int i, int j) {
        if (!areCorrectIndices(i, j)) {
            throw new IndexOutOfBoundsException();
        }

        int gridI = i - 1;
        int gridJ = j - 1;

        if (isOpen(i, j)) {
            for (int k = 0; k < gridSize; k++) {
                if (weightedQuickUnionUF.connected(k, gridI * gridSize + gridJ)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean percolates() {
        for (int i = 0; i < gridSize; i++) {
            if (isFull(gridSize, i + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean areCorrectIndices(int i, int j) {
        return i > 0 && i <= gridSize && j > 0 && j <= gridSize;
    }
}

