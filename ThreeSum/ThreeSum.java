import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreeSum {

    /**
     * Problem:
     * Find all unique triplets in the array whose sum is zero.
     */

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Try all possible triplets using three loops.
     * - Use a Set to avoid duplicate triplets.
     *
     * Time Complexity: O(n^3)
     * Space Complexity: O(n)
     *
     * Stable: Not Applicable
     */
    public static List<List<Integer>> threeSumBrute(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[k]);
                        triplet.sort(Integer::compareTo);
                        set.add(triplet);
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }

    /**
     * Approach 2: Better (Hashing)
     *
     * Idea:
     * - Fix one element.
     * - Use HashSet to find the remaining two elements.
     * - Avoid duplicates using a Set.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     *
     * Stable: Not Applicable
     */
    public static List<List<Integer>> threeSumHashing(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            Set<Integer> seen = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                int third = -nums[i] - nums[j];
                if (seen.contains(third)) {
                    List<Integer> triplet = Arrays.asList(nums[i], nums[j], third);
                    triplet.sort(Integer::compareTo);
                    result.add(triplet);
                }
                seen.add(nums[j]);
            }
        }

        return new ArrayList<>(result);
    }

    /**
     * Approach 3: Optimal (Sorting + Two Pointers)
     *
     * Idea:
     * - Sort the array.
     * - Fix one element.
     * - Use two pointers to find remaining two elements.
     * - Skip duplicates carefully.
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1) (excluding output)
     *
     * Stable: Not Applicable
     */
    public static List<List<Integer>> threeSumOptimal(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};

        System.out.println(threeSumBrute(nums));
        System.out.println(threeSumHashing(nums));
        System.out.println(threeSumOptimal(nums));
    }
}
