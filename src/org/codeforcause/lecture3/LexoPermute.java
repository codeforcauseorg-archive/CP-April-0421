package org.codeforcause.lecture3;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class LexoPermute {
    public static void main(String[] args) {
        String str = "aab";
        int[] freq = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int ind = str.charAt(i) - 'a';
            freq[ind]++;
        }
//        lexoPermute("", str.length(), freq);
        List<String> list = new ArrayList<>();
        lexoPermuteList("", str.length(), freq, list);
        System.out.println(list);
    }

    public static List<String> lexoPermute(String str, int length, int[] freq) {
        List<String> res = new ArrayList<>();
        if (length == 0) {
           res.add(str);
           return res;
        }
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                freq[i]--;
                res.addAll(lexoPermute(str+(char)(i+'a'), length-1, freq));
                freq[i]++;
            }
        }
        return res;
    }

    public static void lexoPermuteList(String str, int length, int[] freq, List<String> list) {
        if (length == 0) {
            list.add(str);
        }
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                freq[i]--;
                lexoPermuteList(str+(char)(i+'a'), length-1, freq, list);
                freq[i]++;
            }
        }
    }
}
