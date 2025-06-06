package longestpalindrome;

import java.util.HashSet;
import java.util.Set;

public class LongestPalindrome {

    Set<String> palindromes = new HashSet<>();

    public boolean isPalindrome(String s) {
        if (palindromes.contains(s)) {
            return true;
        }
        if (s.equals(new StringBuffer(s).reverse().toString())) {
            palindromes.add(s);
            return true;
        }
        return false;
    }

    public String longestPalindrome(String s) {
        int i = 0;
        int l = 0;
        int r = 0;
        int max = 0;
        String longest = "";
        while (i < s.length()) {
            String sub = s.substring(l, r + 1);
            if (isPalindrome(sub)) {
                if (max <= sub.length()) {
                    max = sub.length();
                    longest = sub;
                }
            }
            if (l == 0 || r == s.length() - 1) {
                i++;
                l = i;
                r = i;
            } else {
                l--;
                r++;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        LongestPalindrome p = new LongestPalindrome();
        System.out.println(p.longestPalindrome("cbbd"));
    }
}
