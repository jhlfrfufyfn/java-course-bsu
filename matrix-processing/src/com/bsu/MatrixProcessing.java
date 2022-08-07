package com.bsu;

class MatrixProcessing {
    static int findLongestSeries(int[][] matrix) {
        int maxSeriesLength = -1;
        int bestLineIndex = -1;
        int n = matrix.length;
        int m = matrix[0].length;

        for (int i = 0; i < n; i++) {
            int localBestLength = 1;
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == matrix[i][j - 1]) {
                    localBestLength++;
                } else {
                    if (localBestLength > maxSeriesLength) {
                        maxSeriesLength = localBestLength;
                        bestLineIndex = i;
                    }
                    localBestLength = 1;
                }
            }
            if (localBestLength > maxSeriesLength) {
                maxSeriesLength = localBestLength;
                bestLineIndex = i;
            }
        }

        return bestLineIndex;
    }
}
