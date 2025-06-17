package mindistance;

import java.util.HashMap;
import java.util.Map;

public class MinDistance {

    public int minDistance(String word1, String word2) {
        Map<String, Integer> memo = new HashMap<>();
        return solve(word1, word2, 0, 0, memo);
    }

    public int solve(String word1, String word2, int i, int j, Map<String, Integer> memo) {
        if (i == word1.length() && j == word2.length()) {
            return 0;
        }
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        if (word1.charAt(i) == word2.charAt(j)) {
            return solve(word1, word2, i + 1, j + 1, memo);
        }
        if (memo.containsKey(i + ":" + j)) {
            return 1 + memo.get(i + ":" + j);
        }
        int result = Math.min(solve(word1, word2, i + 1, j, memo), solve(word1, word2, i, j + 1, memo));
        memo.put(i + ":" + j, result);
        return 1 + result;
    }

    public static void main(String[] args) {
        MinDistance m = new MinDistance();
        System.out.println(m.minDistance("sea", "eat"));
    }

}
