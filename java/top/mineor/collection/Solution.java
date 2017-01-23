package top.mineor.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mineor on 2017/1/19.
 */
public class Solution {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        Solution solution = new Solution();
        System.out.println(solution.threeSum(nums));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int low = i + 1;
                int high = nums.length - 1;
                while (low < high) {
                    if (nums[low] + nums[high] == -nums[i]) {
                        result.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1])
                            low++;
                        while (low < high && nums[high] == nums[high - 1])
                            high--;
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] < -nums[i])
                        low++;
                    else
                        high--;
                }
            }
        }
        return result;
    }
}