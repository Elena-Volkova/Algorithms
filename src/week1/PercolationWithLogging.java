package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationWithLogging {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[][] grid;
    private int gridSize;

    public PercolationWithLogging(int n) {
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
            System.out.println("Union left: (" + (indexCurrent + 1) + ", " + (indexLeft + 1) + ") ");
            weightedQuickUnionUF.union(indexCurrent, indexLeft);
        }
        int indexUp = (gridI - 1) * gridSize + gridJ;
        if (areCorrectIndices(i - 1, j) && isOpen(i - 1, j) && !weightedQuickUnionUF.connected(indexCurrent, indexUp)) {
            System.out.println("Union up: (" + (indexCurrent + 1) + ", " + (indexUp + 1) + ") ");
            weightedQuickUnionUF.union(indexCurrent, indexUp);
        }
        int indexRight = gridI * gridSize + gridJ + 1;
        if (areCorrectIndices(i, j + 1) && isOpen(i, j + 1) && !weightedQuickUnionUF.connected(indexCurrent, indexRight)) {
            System.out.println("Union right: (" + (indexCurrent + 1) + ", " + (indexRight + 1) + ") ");
            weightedQuickUnionUF.union(indexCurrent, indexRight);
        }
        int indexDown = (gridI + 1) * gridSize + gridJ;
        if (areCorrectIndices(i + 1, j) && isOpen(i + 1, j) && !weightedQuickUnionUF.connected(indexCurrent, indexDown)) {
            System.out.println("Union down: (" + (indexCurrent + 1) + ", " + (indexDown + 1) + ") ");
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
                System.out.println("Check connected: (" + (k + 1) + ", " + (gridI * gridSize + gridJ + 1) + ") ");
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

    public static void main(String[] args) {
        PercolationWithLogging percolation = new PercolationWithLogging(3);
        openAndCheck(percolation, 1, 2);
        openAndCheck(percolation, 1, 3);
        openAndCheck(percolation, 2, 3);
        openAndCheck(percolation, 3, 3);

    }

    private static void openAndCheck(PercolationWithLogging percolation, int i, int j) {
        System.out.println("Open: (" + i + ", " + j + ")");
        percolation.open(i, j);
        System.out.println("Is open: (" + i + ", " + j + ") " + percolation.isOpen(i, j));
        System.out.println("Is full: (" + i + ", " + j + ") " + percolation.isFull(i, j));
        System.out.println("Percolates: " + percolation.percolates());
    }
}

