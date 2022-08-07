package com.bsu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TailorSeriesTest {

    @Test
    void calculateFunction() {
        final double EPS = 0.001;
        final int PRECISION_DIGITS = 5;
        assertEquals(TailorSeriesCalculator.calculatePreciseValue(-0.99),
                TailorSeriesCalculator.calculateFunction(-0.99, PRECISION_DIGITS), EPS);
        assertEquals(TailorSeriesCalculator.calculatePreciseValue(0.99),
                TailorSeriesCalculator.calculateFunction(0.99, PRECISION_DIGITS), EPS);
        assertEquals(TailorSeriesCalculator.calculatePreciseValue(0),
                TailorSeriesCalculator.calculateFunction(0, PRECISION_DIGITS), EPS);
        assertEquals(TailorSeriesCalculator.calculatePreciseValue(-0.5),
                TailorSeriesCalculator.calculateFunction(-0.5, PRECISION_DIGITS), EPS);
        assertEquals(TailorSeriesCalculator.calculatePreciseValue(0.5),
                TailorSeriesCalculator.calculateFunction(0.5, PRECISION_DIGITS), EPS);
    }
}