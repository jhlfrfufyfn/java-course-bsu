package com.bsu;

class TailorSeriesCalculator {

    static double calculateFunction(double x, int k) {
        final double EPSILON = Math.pow(10., -k);

        double answer = x;
        double difference = Double.MAX_VALUE;
        double currentX = x;
        int currentPower = 1;
        double currentDeliminator = 1;

        while (difference > EPSILON) {
            currentX = currentX * x * x;
            currentPower += 2;
            currentDeliminator *= ((currentPower - 2.) / (currentPower - 1));
            double newTerm = (currentX / (double) currentPower) * currentDeliminator;
            answer += newTerm;
            difference = Math.abs(newTerm);
        }

        return answer;
    }

    static double calculatePreciseValue(double x) {
        return Math.asin(x);
    }
}
