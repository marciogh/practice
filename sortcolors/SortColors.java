package sortcolors;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class SortColors {

    public static void printNums(int[] nums) {
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void sortColors(int[] nums) {
        int lo = 0, mid = 0;
        int hi = nums.length - 1;
        int tmp = 0;
        while (mid <= hi) {
            if (nums[mid] == 0) {
                tmp = nums[lo];
                nums[lo] = nums[mid];
                nums[mid] = tmp;
                lo++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else if (nums[mid] == 2) {
                tmp = nums[mid];
                nums[mid] = nums[hi];
                nums[hi] = tmp;
                hi--;
            }
        }
    }

    public static void main(String[] args) {
        SortColors s = new SortColors();
        int[] nums = { 2, 0, 1 };
        s.sortColors(nums);
        System.out.println("---");
        printNums(nums);
    }

    @Test
    public void test() {
        SortColors s = new SortColors();
        int[] nums;

        nums = new int[] { 2, 0, 2, 1, 1, 0 };
        s.sortColors(nums);
        assertArrayEquals(new int[] { 0, 0, 1, 1, 2, 2 }, nums);

        nums = new int[] { 2, 0, 1 };
        s.sortColors(nums);
        assertArrayEquals(new int[] { 0, 1, 2 }, nums);

        nums = new int[] { 1, 0 };
        s.sortColors(nums);
        assertArrayEquals(new int[] { 0, 1 }, nums);
    }
}
