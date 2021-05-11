package org.codeforcause.lecture8;

public class UniqueIII {
    public static void main(String[] args) {
        int[] nums = {6,9,2,6,8,9,6,2,2,9};

        int[] arr = new int[32];
        for (int i = 0; i < nums.length; i++) {
            int[] arr2 = bitset(nums[i]);
            for (int j = 0; j < 32; j++) {
                arr[j] += arr2[j];
            }
        }
        int number = 0;
        for (int i = 0; i < 32; i++) {
            arr[i] = arr[i] % 3;
            number = number + (int)(arr[i] * Math.pow(2,31 - i));
        }
        System.out.println(number);
    }

    public static int[] bitset(int num) {
        int[] arr = new int[32];
        for (int i = 31; i >= 0; i--) {
            if ((num & 1) == 1) {
                arr[i] = 1;
            }
            num = num >> 1;
        }
        return arr;
    }
}
