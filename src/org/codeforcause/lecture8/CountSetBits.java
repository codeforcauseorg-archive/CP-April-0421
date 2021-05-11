package org.codeforcause.lecture8;

public class CountSetBits {
    public static void main(String[] args) {
        int n = 21;
        System.out.println(countSetBits(n));
    }

    public static int countSetBits(int n) {
        int cnt = 0;
        while (n > 0) {
            if ((n & 1) > 0) {
                cnt++;
            }
            n = n >> 1;
        }
        return cnt;
    }
}
