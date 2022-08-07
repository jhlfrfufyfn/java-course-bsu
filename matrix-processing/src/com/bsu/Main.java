package com.bsu;


import com.sun.media.sound.InvalidFormatException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner cin = new Scanner(System.in)) {

            System.out.print("Enter n: ");
            int n = tryInputInteger(cin);
            if (n <= 0) {
                throw new InvalidFormatException("Error: matrix size can't be non-positive");
            }

            System.out.print("Enter m: ");
            int m = tryInputInteger(cin);
            if (m <= 0) {
                throw new InvalidFormatException("Error: matrix size can't be non-positive");
            }

            int[][] matrix = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    matrix[i][j] = getRandomNumberInRange(0, 10);
                }
            }

            System.out.print("Randomly generated matrix is: \n");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.print('\n');
            }

            System.out.println("The longest series of identical indexes is in line " +
                    MatrixProcessing.findLongestSeries(matrix) +
                    " (zero-indexed)");
        } catch (Exception ex) {
            System.out.println("Error while reading: " + ex);
        }
    }

    private static int tryInputInteger(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.print("Error: that’s not "
                        + "an integer. Try again: ");
            }
        }
    }

    private static int getRandomNumberInRange(int min, int max) {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
