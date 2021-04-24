package org.codeforcause.lecture3;

import java.util.Collections;
import java.util.HashSet;

public class WordBreak {
    public static void main(String[] args) {
        String str = "ilovefootball";
        String[] strings = {"i", "love", "tea", "football", "game", "bal", "ball", "foot"};
        HashSet<String> dictionary = new HashSet<>();
        Collections.addAll(dictionary, strings);
        wordBreak(str, "", dictionary);
    }

    public static void wordBreak(String str, String result, HashSet<String> dictionary) {
        if (str.isEmpty()) {
            System.out.println(result);
            return;
        }
        for (int i = 1; i <= str.length(); i++) {
            String word = str.substring(0,i);
            if (dictionary.contains(word)) {
                wordBreak(str.substring(i), result+word+" ", dictionary);
            }
        }
    }
}
