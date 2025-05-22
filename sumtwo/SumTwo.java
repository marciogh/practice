package sumtwo;

import java.util.Arrays;

public class SumTwo {
    
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target == nums[i] + nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {

        int target = 4;
        int[] nums = {1, 2, 3};

        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}
