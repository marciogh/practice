package countingbits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountingBits {

    public static int countChars(String s, char c) {
        int i = 0;
        int count = 0;
        while (i < s.length()) {
            if (s.charAt(i++) == c) {
                count++;
            }
        }
        return count;
    }

    public int[] countBits(int n) {
        Map<Integer, Integer> memo = new HashMap<>();
        List<Integer> acc = new ArrayList<>();
        countBitsR(n + 1, acc, memo);
        return acc.stream().mapToInt(a -> a).toArray();
    }

    public int countBitsR(int n, List<Integer> acc, Map<Integer, Integer> memo) {
        if (n == 0) {
            return 0;
        }
        acc.add(countBitsR(n - 1, acc, memo));
        if (memo.containsKey(n)) {
            return memo.get(n);
        } else {
            int count = countChars(Integer.toBinaryString(n), '1');
            memo.put(n, count);
            return count;
        }
    }

    public static void main(String[] args) {
        CountingBits c = new CountingBits();
        int[] r = c.countBits(30);
        System.out.println(r);
    }
}
