package org.codeforcause.lecture22;

import java.util.Map;

public class XorTrie {
    class Node {
        Node left;
        Node right;
    }

    Node root;
    XorTrie() {
        this.root = new Node();
    }

    public void addNumber(int n) {
        Node temp = this.root;
        for (int i = 31; i >= 0; i--) {
            int bit = (n >> i) & 1;
            if (bit == 0) {
                if (temp.left == null) {
                    temp.left = new Node();
                }
                temp = temp.left;
            } else {
                if (temp.right == null) {
                    temp.right = new Node();
                }
                temp = temp.right;
            }
        }
    }

    public int maxXorValue(int elt) {
        Node temp = this.root;
        int currXor = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (elt >> i) & 1;
            if (bit == 0) {
                if (temp.right != null) {
                    temp = temp.right;
                    currXor += (int) Math.pow(2,i);
                } else {
                    temp = temp.left;
                }
            } else {
                if (temp.left != null) {
                    temp = temp.left;
                    currXor += (int) Math.pow(2, i);
                } else {
                    temp = temp.right;
                }
            }
        }
        return currXor;
    }

    public static void main(String[] args) {
        int[] nums = {1,0,5,6,3};
        int query = 8;
        XorTrie trie = new XorTrie();
        for(int num : nums) {
            trie.addNumber(num);
        }
        System.out.println(trie.maxXorValue(query));
    }
}
