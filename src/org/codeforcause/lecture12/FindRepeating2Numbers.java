package org.codeforcause.lecture12;

import java.util.HashMap;

public class FindRepeating2Numbers {
    public static void main(String[] args) {
        int[] nums = {1,5,4,2,4,1,3};
        //Brute force
        printRepeating(nums);
        //Time efficient
        findRepeating2nos(nums);
        //maths based
        getRepeating2Nos(nums);
        //Space efficient
        find2RepeatingNos(nums);
    }

    public static void printRepeating(int[] nums) {
        System.out.print("Repeated elements are : ");
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    System.out.print(nums[i] + " ");
                }
            }
        }
        System.out.println();
    }

    public static void findRepeating2nos(int[] nums) {
        System.out.print("Repeated elements are : ");
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
        }
        for (int num : freqMap.keySet()) {
            if (freqMap.get(num) > 1) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }

    public static void find2RepeatingNos(int[] nums) {
        System.out.print("Repeating elements are : ");
        for(int i = 0; i < nums.length; i++) {
            if(nums[Math.abs(nums[i])] > 0) {
                nums[Math.abs(nums[i])] = -nums[Math.abs(nums[i])];
            } else {
                System.out.print(Math.abs(nums[i]) + " ");
            }
        }
        System.out.println();
    }

    public static void getRepeating2Nos(int[] nums) {
        int sum = 0, prod = 1;
        int x, y, diff;
        int n = nums.length - 2;
        for (int num : nums) {
            sum += num;
            prod *= num;
        }
        sum = sum - n*(n + 1)/2;
        prod = prod/factorial(n);
        diff = (int) Math.sqrt(sum*sum - 4*prod);
        x = (diff + sum) / 2;
        y = (sum - diff) / 2;

        System.out.print("Repeating elements are :");
        System.out.println(x + " " + y);
    }

    public static int factorial(int n) {
        return (n == 0) ? 1 : n * factorial(n - 1);
    }
}
