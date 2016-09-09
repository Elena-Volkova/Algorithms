package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private int trials;
    private double[] fractions;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.trials = trials;
        fractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            double openSites = 0;
            Percolation percolation = new Percolation(n);
            do {
                int row;
                int column;
                do {
                    row = StdRandom.uniform(n) + 1;
                    column = StdRandom.uniform(n) + 1;
                } while (percolation.isOpen(row, column));

                percolation.open(row, column);
                openSites++;
            } while (!percolation.percolates());

            fractions[i] = openSites / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(fractions);
    }

    public double stddev() {
        return StdStats.stddev(fractions);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.println("mean: " + percolationStats.mean());
        System.out.println("stddev: " + percolationStats.stddev());
        System.out.println("95% confidence interval: " + percolationStats.confidenceLo() +
                ", " + percolationStats.confidenceHi());
    }
}
