import java.util.HashMap;

public class TwoSum {

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Check every pair (i, j)
     * - If nums[i] + nums[j] == target → return indices
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    public static int[] twoSumBrute(int[] nums, int target) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1}; // not found
    }

    /**
     * Approach 2: HashMap (Optimal for unsorted arrays)
     *
     * Idea:
     * - Use a map to store: value → index
     * - For every number x, check if (target - x) exists in map:
     *      - If yes → pair found
     *      - Else store x in map
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int[] twoSumHashMap(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];

            if (map.containsKey(need)) {
                return new int[]{map.get(need), i};
            }

            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    /**
     * Approach 3: Two Pointers (Only works if array is SORTED)
     *
     * Idea:
     * - Sort array (lose original indices unless stored)
     * - Use left & right pointers to move inward
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(1) or O(n) depending on storage of indices
     */
    public static int[] twoSumTwoPointer(int[] nums, int target) {
        int n = nums.length;

        // Create a pair array so we keep track of original indices
        int[][] pair = new int[n][2];
        for (int i = 0; i < n; i++) {
            pair[i][0] = nums[i]; // value
            pair[i][1] = i;       // index
        }

        java.util.Arrays.sort(pair, (a, b) -> a[0] - b[0]);

        int left = 0, right = n - 1;

        while (left < right) {
            int sum = pair[left][0] + pair[right][0];

            if (sum == target) {
                return new int[]{pair[left][1], pair[right][1]};
            }

            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;

        System.out.println(java.util.Arrays.toString(twoSumBrute(nums, target)));
        System.out.println(java.util.Arrays.toString(twoSumHashMap(nums, target)));
        System.out.println(java.util.Arrays.toString(twoSumTwoPointer(nums, target)));
    }
}
