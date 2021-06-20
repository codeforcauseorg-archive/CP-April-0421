package org.codeforcause.lecture22;

import java.util.HashMap;
import java.util.Map;

public class DictionaryTrie {
    class Node {
        char data;
        boolean terminal;
        Map<Character, Node> children;

        Node(char data) {
            this.data = data;
            this.children = new HashMap<>();
        }
    }

    Node root;

    DictionaryTrie() {
        this.root = new Node('\0');
    }

    public void insertWord(String str) {
        Node temp = this.root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (temp.children.containsKey(ch)) {
                temp = temp.children.get(ch);
            } else {
                Node node = new Node(ch);
                temp.children.put(ch, node);
                temp = node;
            }
        }
        temp.terminal = true;
    }

    public boolean search(String str) {
        Node temp = this.root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (temp.children.containsKey(ch)) {
                temp = temp.children.get(ch);
            } else {
                return false;
            }
        }
        return temp.terminal;
    }

    public boolean searchPrefix(String str) {
        Node temp = this.root;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (temp.children.containsKey(ch)) {
                temp = temp.children.get(ch);
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"app", "apple", "butterfly"};
        DictionaryTrie trie = new DictionaryTrie();
        for(String str : words) {
            trie.insertWord(str);
        }

        System.out.println(trie.searchPrefix("but"));
        System.out.println(trie.search("app"));
        System.out.println(trie.search("butter"));
    }
}
