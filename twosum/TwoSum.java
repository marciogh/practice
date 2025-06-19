package twosum;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            index.put(nums[i], i);
        }
        for (int j : index.keySet()) {
            int diff = target - j;
            if (index.containsKey(diff)) {
                return new int[] { index.get(j), index.get(diff) };
            }
        }
        return new int[] { 0, 0 };

    }

    public static void main(String[] args) {
        TwoSum t = new TwoSum();
        int[] r = t.twoSum(new int[] { 1, 2, 3 }, 3);
        System.out.println(r[0]);
        System.out.println(r[1]);

    }
}