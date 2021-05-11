package org.codeforcause.lecture7;

import java.util.Arrays;

public class LongIncSubseq {
    public static void main(String[] args) {
        int[] nums = {10, 2, 14, 6, 8, 18, 24};
        System.out.println(longIncSubseqDP(nums));
    }

    private static int longIncSubseqDP(int[] nums) {
        int[] mem = new int[nums.length];
        Arrays.fill(mem, 1);
        for (int ind = 0; ind < nums.length; ind++) {
            for (int prev = 0; prev < ind; prev++) {
                if (nums[ind] > nums[prev] && mem[ind] < mem[prev]+1) {
                    mem[ind] = mem[prev] + 1;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < mem.length; i++) {
            if (max < mem[i]) {
                max = mem[i];
            }
        }
        return max;
    }
}
