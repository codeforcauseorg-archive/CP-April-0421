package org.codeforcause.lecture9;

public class InclusionExclusionPrimes {
    public static void main(String[] args) {
        int[] primes = {2,3,5,7,11,13,17,19};
        int n = 1000;
        int ans = 0;
        int subsets = (1<<8) - 1; //2^8 - 1;
        for (int i = 1; i <= subsets; i++) {
            int denom = 1;
            int sign = countBits(i);
            int t = i;
            for (int j = 0; j < primes.length; j++) {
                if ((t & 1) > 0) {
                    denom = denom * primes[j];
                }
                t = t >> 1;
            }
            ans += (sign * (n/denom));
        }

        System.out.println("Answer: " + ans);
    }

    private static int countBits(int n) {
        int i = 0;
        while (n > 0) {
            if ((n&1) > 0) {
                i++;
            }
            n = n >> 1;
        }
        return i%2 == 0 ? -1 : 1;
    }
}
