package com.bsu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class LongestSeriesTest {
    @Test
    void findLongestSeries() {
        int[][] testMatrix1 = {{1, 1},
                {1, 1},
                {1, 1}};
        assertEquals(MatrixProcessing.findLongestSeries(testMatrix1), 0);

        int[][] testMatrix2 = {{1},
                {2},
                {3},
                {4}};
        assertEquals(MatrixProcessing.findLongestSeries(testMatrix2), 0);

        int[][] testMatrix3 = { {1, 2, 3, 4},
                {2, 2, 3, 4},
                {3, 4, 5, 6},
                {4, 1, 2, 3}};
        assertEquals(MatrixProcessing.findLongestSeries(testMatrix3), 1);

        int[][] testMatrix4 = { {1, 2, 3, 4, 5},
                {1, 2, 3, 4, 5},
                {0, 0, 0, 0, 0}};
        assertEquals(MatrixProcessing.findLongestSeries(testMatrix4), 2);

        int[][] testMatrix5 = { {1, 2, 3, 4, 5},
                {0, 1, 1, 3, 5}};
        assertEquals(MatrixProcessing.findLongestSeries(testMatrix5), 1);

    }

}