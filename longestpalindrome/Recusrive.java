package longestpalindrome;

public class Recusrive {

    static void lps(String s, int left, int right) {
        if (left > right) {
            return;
        }
        System.out.println(s.substring(left, right + 1));
        lps(s, left, right - 1);
        lps(s, left + 1, right);
    }

    static void longestPalinSubseq(String s) {
        lps(s, 0, s.length() - 1);
    }

    public static void main(String[] args) {
        String s = "abcdef";
        longestPalinSubseq(s);
    }
}
