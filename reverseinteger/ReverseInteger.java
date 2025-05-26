package reverseinteger;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReverseInteger {
    
    public int reverse(int x) {
        if (x == 0) {
            return 0;
        }
        boolean negative = false;
        if (x < 0) {
            x = x * -1;
            negative = true;
        }
        int rev = 0;
        int lastDigit = 0;
        while (x > 0) {
            lastDigit = x % 10;
            x = x / 10;
            if (rev > Integer.MAX_VALUE / 10) { // overflow
                return 0;
            }
            rev = (int)(rev * 10 + lastDigit);
            if (rev < 0) { // overflow
                return 0;
            }
        }
        if (negative) {
            rev = rev * - 1;
        }
        return rev;
    }

    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
        System.out.println(1534236469);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(r.reverse(1534236469));
    }

    @Test
    public void test() {
        ReverseInteger r = new ReverseInteger();
        assertEquals(0, r.reverse(0));
        assertEquals(-1, r.reverse(-1));
        assertEquals(321, r.reverse(123));
        assertEquals(-321, r.reverse(-123));
        assertEquals(21, r.reverse(120));
        assertEquals(0, r.reverse(Integer.MAX_VALUE - 10));
        assertEquals(0, r.reverse(Integer.MIN_VALUE + 20));
        assertEquals(0, r.reverse(1534236469));
    }
}
