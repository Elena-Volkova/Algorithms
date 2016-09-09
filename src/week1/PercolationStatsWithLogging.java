package week1;

import java.util.Random;

public class PercolationStatsWithLogging {

    private int trials;
    private double[] fractions;

    public PercolationStatsWithLogging(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.trials = trials;
        fractions = new double[trials];

        for (int i = 0; i < trials; i++) {
            double fraction = 0;
            Percolation percolation = new Percolation(n);
            Random random = new Random();
            do {
                fraction++;
                int row = random.nextInt(n) + 1;
                int column = random.nextInt(n) + 1;

                System.out.println("Trial: " + (i + 1) + ", fraction: " + fraction);
                System.out.println("Open: (" + row + ", " + column + ")");
                percolation.open(row, column);
            } while (!percolation.percolates());

            fractions[i] = fraction;
        }
    }

    public double mean() {
        double sumFractions = 0;

        for (double fraction : fractions) {
            sumFractions += fraction;
        }

        return sumFractions / trials;
    }

    public double stddev() {
        double mean = mean();
        double sumDeviations = 0;

        for (double fraction : fractions) {
            sumDeviations += Math.pow(fraction - mean, 2);
        }

        return sumDeviations / (trials - 1);
    }

    public double confidenceLo() {
        double mean = mean();
        double stddev = stddev();

        return mean - 1.96 * Math.sqrt(stddev) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        double mean = mean();
        double stddev = stddev();

        return mean + 1.96 * Math.sqrt(stddev) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        PercolationStatsWithLogging percolationStats = new PercolationStatsWithLogging(4, 4);
        System.out.println("Mean: " + percolationStats.mean());
        System.out.println("Stddev: " + percolationStats.stddev());
        System.out.println("ConfidenceLo: " + percolationStats.confidenceLo());
        System.out.println("ConfidenceHi: " + percolationStats.confidenceHi());
    }
}
