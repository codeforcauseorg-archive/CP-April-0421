package org.codeforcause.lecture6;

public class ZeroOneKnapSack {
    public static void main(String[] args) {
        int[] weights = {10, 20, 30};
        int[] profits = {60, 100, 120};
        int cap = 50;
        Integer[][] mem = new Integer[cap+1][weights.length+1];
        System.out.println(zeroOne(weights, profits, cap, weights.length));
        System.out.println(zeroOneDP(weights, profits, cap, weights.length, mem));
        System.out.println(zeroOneItr(weights, profits, cap, weights.length, mem));
    }

    private static int zeroOne(int[] weights, int[] profits, int cap, int size) {
        if (size == 0) {
            return 0;
        }
        int accept = 0, reject = 0;
        if (weights[size-1] <= cap) {
            accept = profits[size-1] + zeroOne(weights, profits, cap-weights[size-1], size-1);
        }
        reject = zeroOne(weights, profits, cap, size-1);
        return Math.max(accept, reject);
    }

    private static int zeroOneDP(int[] weights, int[] profits, int cap, int size, Integer[][] mem) {
        if (size == 0) {
            return 0;
        }
        if (mem[cap][size] != null) {
            return mem[cap][size];
        }
        int accept = 0, reject = 0;
        if (weights[size-1] <= cap) {
            accept = profits[size-1] + zeroOneDP(weights, profits, cap-weights[size-1], size-1, mem);
        }
        reject = zeroOneDP(weights, profits, cap, size-1, mem);
        mem[cap][size] = Math.max(accept, reject);
        return mem[cap][size];
    }
    
    public static int zeroOneItr(int[] weights, int[] profits, int cap, int size, Integer[][] mem) {
        for (int c = 0; c <= cap; c++) {
            for (int s = 0; s <= size; s++) {
                if (s == 0) {
                    mem[c][s] = 0;
                } else {
                    int accept = 0, reject = 0;
                    if (weights[s - 1] <= c) {
                        accept = profits[s - 1] + mem[c-weights[s-1]][s-1];
                    }
                    reject = mem[c][s-1];
                    mem[c][s] = Math.max(accept, reject);
                }
            }
        }
        return mem[cap][size];
    }
}
