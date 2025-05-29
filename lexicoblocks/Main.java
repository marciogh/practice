package beforepay;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;

public class Main {

    public static boolean isValid(String s) {
        Stack<Character> cs = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '{' || c == '(') {
                cs.add(c);
            }
            if (c == ']' || c == '}' || c == ')') {
                if (cs.isEmpty()) {
                    return false;
                }
                char pop = cs.pop();
                if (pop == '[' && c != ']') {
                    return false;
                }
                if (pop == '{' && c != '}') {
                    return false;
                }
                if (pop == '(' && c != ')') {
                    return false;
                }
            }
        }
        return cs.isEmpty();
    }

    public static void main(String[] args) {
    }

    @Test
    public void test() {
        assertTrue(isValid("()"));
        assertTrue(isValid("((asdasd((((()))))))"));
        assertFalse(isValid("((((((()))))))]"));
        assertFalse(isValid("{{{{]a}}}}"));
    }
}
