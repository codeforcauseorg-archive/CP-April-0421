package org.codeforcause.lecture16;

public class SegmentTree {
    public static void main(String[] args) {
        int[] nums = {-2, 1, 0, -3, 4, -6};
        Integer[] tree = new Integer[4*nums.length+1];
        buildTree(nums, tree, 0, nums.length-1, 1);
        int ans = query(tree, 0, nums.length-1, 1, 4, 1);
        System.out.println(ans);
        nums[3] = 8;
        ans = query(tree, 0, nums.length-1,3,4,1);
        System.out.println(ans);
        update(tree, 1, 0, nums.length-1, 3, 8);
        ans = query(tree, 0, nums.length-1,3,4,1);
        System.out.println(ans);
    }

    private static int query(Integer[] tree, int nodeStart, int nodeEnd,
                              int queryStart, int queryEnd, int nodeIndex) {
        //Complete Overlap
        if (nodeStart >= queryStart && nodeEnd <= queryEnd) {
            return tree[nodeIndex];
        }

        //No Overlap
        if (nodeEnd < queryStart || nodeStart > queryEnd) {
            return Integer.MAX_VALUE;
        }

        //Partial Overlap
        int mid = (nodeStart + nodeEnd)/2;
        int leftAns = query(tree, nodeStart, mid, queryStart, queryEnd, 2*nodeIndex);
        int rightAns = query(tree, mid+1, nodeEnd, queryStart, queryEnd, 2*nodeIndex+1);
        return Math.min(leftAns, rightAns);
    }

    public static void buildTree(int[] nums, Integer[] tree, int start, int end, int index) {
        if (start == end) {
            tree[index] = nums[start];
            return;
        }
        int mid = (start+end)/2;
        buildTree(nums, tree, start, mid, 2*index);
        buildTree(nums, tree, mid+1, end, 2*index+1);
        tree[index] = Math.min(tree[2*index], tree[2*index+1]);
    }

    public static void update(Integer[] tree, int nodeIndex, int nodeStart, int nodeEnd, int numsInd, int newValue) {
        if (numsInd < nodeStart || numsInd > nodeEnd) {
            return;
        }
        if (nodeEnd == nodeStart) {
            tree[nodeIndex] = newValue;
            return;
        }

        int mid = (nodeStart+nodeEnd)/2;
        update(tree, 2*nodeIndex, nodeStart, mid, numsInd, newValue);
        update(tree, 2*nodeIndex+1, mid+1, nodeEnd, numsInd, newValue);
        tree[nodeIndex] = Math.min(tree[2*nodeIndex], tree[2*nodeIndex+1]);
    }
}
