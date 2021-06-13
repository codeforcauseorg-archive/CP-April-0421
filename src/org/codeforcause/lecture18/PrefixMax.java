package org.codeforcause.lecture18;

import java.util.Scanner;

public class PrefixMax {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] nums = new int[n];
        int[] BIT = new int[n+1];
        for (int i = 1; i <= n; i++) {
            nums[i] = s.nextInt();
            update(i,nums[i], n, BIT);
        }
        int q = s.nextInt();
        while (q-- > 0){
            int ind = s.nextInt();
            System.out.println("Max till " + ind + " : " + query(ind, BIT));
        }
    }

    public static void update(int i, int val, int n, int[] BIT){
        while (i <= n){
            BIT[i] = Math.max(BIT[i], val);
            i += (i & (-i));
        }
    }

    public static int query(int i, int[] BIT){
        int ans = 0;
        while (i > 0){
            ans = Math.max(BIT[i], ans);
            i -= (i & (-i));
        }
        return ans;
    }
}
