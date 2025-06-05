package canva;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * calculate expressions like 3 - 1 * 4 ^ 6
 */
public class Canva {

    static final char POWER = '^';
    static final char MULTIPLY = '*';
    static final char SUBTRACT = '-';

    public int resolve(String expr) {
        String left;
        String right;
        expr = expr.trim();
        if (expr.isEmpty()) {
            expr = "0";
        }
        int sub = expr.indexOf(SUBTRACT);
        if (sub > -1) {
            left = expr.substring(0, sub);
            right = expr.substring(sub + 1);
            return resolve(left) - resolve(right);
        } else {
            int multi = expr.indexOf(MULTIPLY);
            if (multi > -1) {
                left = expr.substring(0, multi);
                right = expr.substring(multi + 1);
                return resolve(left) * resolve(right);
            } else {
                int power = expr.indexOf(POWER);
                if (power > -1) {
                    left = expr.substring(0, power);
                    right = expr.substring(power + 1);
                    return (int) Math.pow((double) resolve(left), resolve(right));
                }
            }
        }
        return Integer.valueOf(expr);
    }

    public static void main(String[] args) {
        Canva c = new Canva();
        int r = c.resolve("10 ^ 3 - 998");
        System.out.println(r);
    }

    @Test
    public void test() {
        Canva c = new Canva();
        assertEquals(2, c.resolve("3 - 1 ^ 2"));
        assertEquals(8, c.resolve("10 - 2"));
        assertEquals(-8, c.resolve("2 - 10"));
        assertEquals(-8, c.resolve("      2  -  10      "));
        assertEquals(1000, c.resolve("10 ^ 3"));
        assertEquals(2, c.resolve("10 ^ 3 - 998"));
        assertEquals(20, c.resolve("50 - 10 * 3"));
    }

}
