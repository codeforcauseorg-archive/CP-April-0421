package org.codeforcause.lecture19;

import java.util.Arrays;

public class PrefixMax {
    public static void main(String[] args) {
        int n = 5;
        int[] nums = {-2, 4, 3, 7, -1};
        int[] BIT = new int[n+1];

        Arrays.fill(BIT, Integer.MIN_VALUE);
        for (int i = 0; i < nums.length; i++) {
            update(i+1, nums[i], n, BIT);
        }

        int queryInd = 1;
        System.out.println(query(queryInd, BIT));
    }

    private static void update(int ind, int val, int n, int[] BIT) {
        while (ind <= n) {
            BIT[ind] = Math.max(BIT[ind], val);
            ind += (ind & (-ind));
        }
    }

    public static int query(int ind, int[] BIT) {
        int max = Integer.MIN_VALUE;
        while (ind > 0) {
            max = Math.max(BIT[ind], max);
            ind -= (ind & (-ind));
        }
        return max;
    }
}
