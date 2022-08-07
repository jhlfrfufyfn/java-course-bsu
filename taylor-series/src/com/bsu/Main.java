package com.bsu;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

import com.sun.media.sound.InvalidFormatException;


public class Main {

    public static void main(String[] args) {
        ///the locale is for printing floating point instead of comma
        try (Scanner cin = new Scanner(System.in).useLocale(Locale.ENGLISH)) {
            double x;
            System.out.print("Enter x(with a point): ");

            x = getDouble(cin);
            if (-1. > x || x > 1.) {
                throw new InvalidFormatException("Error: boundaries for x are (-1; 1).");
            }

            System.out.print("Enter k: ");
            int k = getInteger(cin);

            output(TailorSeriesCalculator.calculateFunction(x, k), TailorSeriesCalculator.calculatePreciseValue(x));
        } catch (Exception ex) {
            System.out.println("Error while reading: " + ex);
        }
    }

    private static String formattedNumericValue(double value) {
        return String.format(java.util.Locale.US, "%.3f", value);
    }

    private static void output(double answer, double correctAnswer) {
        ///the locale is for printing floating point instead of comma
        System.out.println("my function arcsin(x) = " + formattedNumericValue(answer));
        System.out.println("standart function arcsin(x) = " + formattedNumericValue(correctAnswer));
    }

    private static int getInteger(Scanner sc) {
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

    private static double getDouble(Scanner sc) {
        while (true) {
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                sc.next();
                System.out.print("Error: that’s not "
                        + " double. Try again: ");
            }
        }
    }
}

