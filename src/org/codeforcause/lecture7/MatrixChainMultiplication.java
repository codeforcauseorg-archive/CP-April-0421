package org.codeforcause.lecture7;

public class MatrixChainMultiplication {
    public static void main(String[] args) {
        int[] dim = {4, 2, 3, 1, 3};
        matrixChainMultiplication(dim, 1, dim.length-1);
    }

    private static int matrixChainMultiplication(int[] dim, int start, int end) {
        if (start == end) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = start; k < end; k++) {
            int operations = matrixChainMultiplication(dim, start, k) +
                    matrixChainMultiplication(dim, k+1, end) + dim[start-1]*dim[k]*dim[end];
            if (operations < min) {
                min = operations;
            }
        }
        return min;
    }

    private static int matrixChainMultiDP(int[] dim) {
        int[][] mem = new int[dim.length][dim.length];
        for (int len = 2; len < dim.length; len++) {
            for (int start = 1; start < dim.length-len+1; start++) { // intermediate start
                int end = start + len - 1; // intermediate end
                if (end == dim.length) {
                    continue;
                }
                mem[start][end] = Integer.MAX_VALUE;
                for (int k = start; k < end; k++) {
                    int countOp = mem[start][k] + mem[k+1][end] + dim[start-1]*dim[k]*dim[end];
                    if (countOp < mem[start][end]) {
                        mem[start][end] = countOp;
                    }
                }
            }
        }
        return mem[1][dim.length-1];
    }
}
