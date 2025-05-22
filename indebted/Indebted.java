package indebted;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

/*

* function binarySearch(array, target) {
    // Implement the binary search algorithm
    return -1;
}

// Test cases
console.log(binarySearch([-5, 1, 3, 7, 9, 12, 14], 9));  // Should return 4
console.log(binarySearch([-15, -10, -3, 0, 5, 8, 12], 6));  // Should return -1

 */
public class Indebted {

    private int binarySearch(List<Integer> array, int target) {
        return binarySearchRecursive(array, 0, array.size() - 1, target);
    }

    private int binarySearchRecursive(List<Integer> array, int start, int end, int target) {
        if (start >= end) {
            if (array.get(start) == target) {
                return start;
            } else {
                return -1;
            }
        } else {
            int middle = (int)(start + end) / 2;
            int last = array.size() - 1;
            if (array.get(middle) == target) {
                return middle;
            }
            if (target < array.get(middle)) {
                System.out.println(String.format("left %d %d", start, middle - 1));
                return binarySearchRecursive(array, start, middle - 1, target);
            } else {
                if (last - middle == 1) {
                    middle++;
                }
                System.out.println(String.format("right %d %d", middle, last));
                return binarySearchRecursive(array, middle, array.size() - 1, target);
            }

        }
    }


    @Test
    public void testBinarySearch() {

        List<Integer> numbers;
        numbers = List.of(-5, 1, 3, 7, 9, 12, 14);
        assertEquals(4, binarySearch(numbers, 9));

        numbers = List.of(-15, -10, -3, 0, 5, 8, 12);
        assertEquals(-1, binarySearch(numbers, 6));

        numbers = List.of(-100, -90, -70, -50, -30, -10, 0, 10, 22, 23, 45, 56, 67, 99);
        assertEquals(-1, binarySearch(numbers, 15));
        assertEquals(2, binarySearch(numbers, -70));
        assertEquals(12, binarySearch(numbers, 67));
        assertEquals(0, binarySearch(numbers, -100));
        assertEquals(13, binarySearch(numbers, 99));
        assertEquals(0, binarySearch(numbers, -100));

        numbers = List.of(-100, -90, -70, -50, -30, -10, 0, 10, 22, 23, 45, 56, 67, 99, 100);
        assertEquals(14, binarySearch(numbers, 100));
        
    }
}
