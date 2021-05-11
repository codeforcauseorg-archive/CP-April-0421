package org.codeforcause.lecture7;

public class MaxSubarraySum {
    public static void main(String[] args) {
        int[] nums = {-2, 4, 2, 6, -1, 3, -8, -2, 2};
        System.out.println(maxSubarraySum(nums));
    }

    public static int maxSubarraySum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int max = nums[0];
        int curr_max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curr_max = Math.max(nums[i], curr_max+nums[i]);
            max = Math.max(max, curr_max);
        }
        return max;
    }
}
