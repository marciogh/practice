package predictwinner;

import java.util.HashMap;
import java.util.Map;

public class PredictWinner {

  public boolean predictTheWinner(int[] nums) {
    if (nums.length == 1) {
      return true;
    }
    return ps(nums, 0, nums.length - 1, new HashMap<String, Integer>()) >= 0;
  }

  public int ps(int[] nums, int left, int right, Map<String, Integer> memo) {
    if (left == right) {
      return nums[left];
    }
    if (memo.containsKey(left + ":" + right)) {
      return memo.get(left + ":" + right);
    }
    int result = Math.max(nums[left] - ps(nums, left + 1, right, memo), nums[right] - ps(nums, left, right - 1, memo));
    memo.put(left + ":" + right, result);
    return result;
  }

  public static void main(String[] args) {
    PredictWinner n = new PredictWinner();
    System.out.println(n.predictTheWinner(new int[] { 1, 2, 99 }));
  }
}
