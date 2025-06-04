package expression;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class Expression {

    public int resolve(String expr) {

        if (expr.isEmpty()) {
            return 0;
        }

        expr = expr.trim();

        int open = expr.indexOf('(');
        int close = expr.lastIndexOf(')');
        int addition = expr.indexOf('+');

        if (open > -1 && close > -1) {

            String left = expr.substring(0, open);
            String middle = expr.substring(open + 1, close);
            String right = expr.substring(close + 1);

            // ternary recursion
            return resolve(left) + resolve(middle) + resolve(right);

        } else if (addition > -1) {

            String left = expr.substring(0, addition).trim();
            String right = expr.substring(addition + 1).trim();

            // binary recursion
            return resolve(left) + resolve(right);

        } else {

            // recursion termination
            return Integer.valueOf(expr.trim());

        }
    }

    @Test
    public void test() {
        Expression e = new Expression();
        assertEquals(11, e.resolve("1+(3+5)+2"));
        assertEquals(11, e.resolve("1 + (3 + 5) + 2"));
        assertEquals(11, e.resolve("          1 + (3 + 5) + 2"));
        assertEquals(12, e.resolve("1 + ((3 + 5)) + 3"));
        assertEquals(13, e.resolve("(3 + ((3 + 5)) + 2)"));
        assertEquals(14, e.resolve("   (3 +  ( (5 + 4)) +   2  )"));
        assertEquals(15, e.resolve("5 + (3 + 5) + 2"));

        // broken for lacking parenthesis stack analysis :(
        assertThrows(StringIndexOutOfBoundsException.class, () -> e.resolve("1 + () (2 + 3)"));
    }
}
