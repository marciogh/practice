package climb;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Climb {
    
    public int downStairsRecursive(int n, int total, Map<Integer, Integer> memo) {
        if (n == 0) {
            total+= 1;
        }
        if (n <= 0) {
            return total;
        }
        if (memo.containsKey(n)) {
            return memo.get(n);
        } else {
                int calculatedTotal = 
                    downStairsRecursive(n - 1, total, memo)
                    +
                    downStairsRecursive(n - 2, total, memo);
                memo.put(n, calculatedTotal);    
                return calculatedTotal;
        }
    }

    public int climbStairs(int n) {
        if (n == 0) {
            return 0;
        }
        return downStairsRecursive(n, 0, new HashMap<Integer, Integer>());
    }

    @Test
    public void testClimb() {
        Climb c = new Climb();
        assertEquals(0, c.climbStairs(0));
        assertEquals(2, c.climbStairs(2));
        assertEquals(3, c.climbStairs(3));
    }

    @Test
    public void testClimbLong() {
        Climb c = new Climb();
        assertEquals(165580141, c.climbStairs(40));
        assertEquals(1836311903, c.climbStairs(45));
    }

}
