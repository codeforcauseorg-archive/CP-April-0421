package org.codeforcause.lecture10;

public class CountMax1sinRow {
    public static void main(String[] args) {
        int[][] matrix = {{0,0,0,1,1},
                          {0,1,1,1,1},
                          {0,0,0,0,0},
                          {1,1,1,1,1}};
        System.out.println(rowWithMax1s(matrix));
        System.out.println(rowWithMaxNoOf1s(matrix));
    }

    //Binary Search Approach: O(n*logm)
    public static int rowWithMax1s(int[][] matrix) {
        int ansRow = 0, max1s = -1;
        int index;
        for (int i = 0; i < matrix.length; i++) {
            index = firstOccurrenceOf1(matrix[i], 0, matrix[i].length - 1);
            if (index != -1 && matrix[i].length - index > max1s) {
                max1s = matrix[i].length - index;
                ansRow = i;
            }
        }
        return ansRow;
    }

    //O(m+n) approach
    public static int rowWithMaxNoOf1s(int[][] matrix) {
        int ansRow = 0;
        int index = firstOccurrenceOf1(matrix[0], 0, matrix[0].length - 1);
        if (index == -1) { // if 1 is not present in first row
            index = matrix[0].length - 1;
        }
        for (int i = 1; i < matrix.length; i++) {
            while (index >= 0 && matrix[i][index] == 1) {
                index = index - 1;  // Update the index of leftmost 1 seen so far
                ansRow = i;  // Update ansRow
            }
        }
        return ansRow;
    }

    public static int firstOccurrenceOf1(int[] nums, int low, int high) {
        if (high >= low) {
            int mid = low + (high - low) / 2;
            if ((mid == 0 || (nums[mid - 1] == 0)) && nums[mid] == 1) {
                return mid;
            } else if (nums[mid] == 0) {
                return firstOccurrenceOf1(nums, (mid + 1), high);
            } else {
                return firstOccurrenceOf1(nums, low, (mid - 1));
            }
        }
        return -1;
    }
}
