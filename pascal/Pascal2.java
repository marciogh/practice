package pascal;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/* [
 * [1],
 * [1,1],
 * [1,2,1],
 * [1,3,3,1],
 * [1,4,6,4,1]
 * ]
 */
public class Pascal2 {
    
    public List<List<Integer>> pascal(int height) {
        return pascalRecursive(1, height, new ArrayList<List<Integer>>());
    }

    public List<List<Integer>> pascalRecursive(int line, int limit, List<List<Integer>> triangle) {
        if (line > limit) {
            return triangle;
        }
        if (line == 1) {
            triangle.add(List.of(1));
            return pascalRecursive(line + 1, limit, triangle);
        }
        if (line == 2) {
            triangle.add(
                List.of(1,1)
            );
            return pascalRecursive(line + 1, limit, triangle);
        }
        List<Integer> previousLine = triangle.get(line - 2);
        List<Integer> newLine = new ArrayList<Integer>();
        newLine.add(1);
        for (int i = 1; i < previousLine.size(); i ++) {
            newLine.add(previousLine.get(i - 1) + previousLine.get(i));
        }
        newLine.add(1);
        triangle.add(newLine);
        return pascalRecursive(line + 1, limit, triangle);
    }

    @Test
    public void testInitialHeights() {
        Pascal2 p = new Pascal2();
        assertEquals(List.of(), p.pascal(0));
        assertEquals(List.of(List.of(1)), p.pascal(1));
        assertEquals(
            List.of(
                List.of(1),
                List.of(1,1)
            ), p.pascal(2)
        );
    }

    @Test
    public void testMediumHeights() {
        Pascal2 p = new Pascal2();
        assertEquals(
            List.of(
                List.of(1),
                List.of(1,1),
                List.of(1,2,1),
                List.of(1,3,3,1)
            ), p.pascal(4)
        );
        assertEquals(
            List.of(
                List.of(1),
                List.of(1,1),
                List.of(1,2,1),
                List.of(1,3,3,1),
                List.of(1,4,6,4,1)
            ), p.pascal(5)
        );
    }
}
