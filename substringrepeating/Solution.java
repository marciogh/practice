package substringrepeating;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        
        if (s.length() <= 1) {
            return s.length();
        }

        String charStack;
        int start = 0, end = 1, max = 0;
        while (end < s.length()) {
            charStack = s.substring(start, end);
            Character nextChar = s.charAt(end);
            if (! charStack.contains(String.valueOf(nextChar))) {
                end ++;
            } else {
                start ++;
            }
            if (end - start > max) {
                max = end - start;
            }
        };

        return max;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.lengthOfLongestSubstring("abcabcbb"));
    }

    @Test
    public void test() {
        Solution s = new Solution();
        assertEquals(3, s.lengthOfLongestSubstring("abcabcbb"));
        assertEquals(1, s.lengthOfLongestSubstring("bbbbb"));
        assertEquals(2, s.lengthOfLongestSubstring("aab"));
        assertEquals(3, s.lengthOfLongestSubstring("pwwkew"));
        assertEquals(0, s.lengthOfLongestSubstring(""));
        assertEquals(2, s.lengthOfLongestSubstring("cdd"));
        assertEquals(2, s.lengthOfLongestSubstring("cdddddd"));
        assertEquals(3, s.lengthOfLongestSubstring("ohomm"));
        assertEquals(3, s.lengthOfLongestSubstring("dvdf"));
        assertEquals(1, s.lengthOfLongestSubstring(" "));
    }

}
