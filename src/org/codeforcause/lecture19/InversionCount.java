package org.codeforcause.lecture19;

public class InversionCount {
    public static void main(String[] args) {
        int n = 4;
        int[] nums = {3, 4, 1, 2};
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(nums[i], max);
        }
        int[] BIT = new int[max+1];
        int count = 0;
        for (int i = n-1; i >= 0; i--) {
            count += query(nums[i]-1, BIT);
            update(nums[i], 1, max, BIT);
        }
        System.out.println(count);
    }

    public static int query(int ind, int[] BIT) {
        int ans = 0;
        while (ind > 0) {
            ans += BIT[ind];
            ind -= (ind & (-ind));
        }
        return ans;
    }

    public static void update(int ind, int val, int n, int[] BIT) {
        while (ind <= n) {
            BIT[ind] += val;
            ind += (ind & (-ind));
        }
    }
}
