package org.codeforcause.Lecture7;

public class WildcardPattern {
    public static void main(String[] args) {
        String pattern = "**e??a";
        String str = "ekta";
        System.out.println(wildcardMatching(pattern, str));
    }

    private static boolean wildcardMatching(String pattern, String str) {
        if (pattern.isEmpty()) {
            return str.isEmpty();
        }
        boolean[][] mem = new boolean[str.length()+1][pattern.length()+1];
        mem[0][0] = true;
        for (int i = 1; i <= pattern.length(); i++) {
            if (pattern.charAt(i-1) == '*') {
                mem[0][i] = mem[0][i - 1];
            }
        }
        for (int i = 1; i <= str.length(); i++) {
            for (int j = 1; j <= pattern.length(); j++) {
                if (pattern.charAt(j-1) == '*') {
                    mem[i][j] = mem[i][j-1] || mem[i-1][j];
                } else if (pattern.charAt(j-1) == '?') {
                    mem[i][j] = mem[i-1][j-1];
                } else if (pattern.charAt(j-1) == str.charAt(i-1)) {
                    mem[i][j] = mem[i-1][j-1];
                } else {
                    mem[i][j] = false;
                }
            }
        }
        return mem[str.length()][pattern.length()];
    }
}
