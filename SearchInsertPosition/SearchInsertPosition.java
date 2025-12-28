
public class SearchInsertPosition {

    /**
     * Problem: Find the index of target in a sorted array. If not found, return
     * the index where it should be inserted.
     */
    /**
     * Approach 1: Brute Force
     *
     * Idea: - Traverse the array. - Return first index where arr[i] >= target.
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int searchInsertBrute(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea: - Use binary search to find first index where nums[mid] >= target.
     *
     * Time Complexity: O(log n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int searchInsertOptimal(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int ans = nums.length;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};

        System.out.println(searchInsertBrute(nums, 5));
        System.out.println(searchInsertOptimal(nums, 5));

        System.out.println(searchInsertBrute(nums, 2));
        System.out.println(searchInsertOptimal(nums, 2));

        System.out.println(searchInsertBrute(nums, 7));
        System.out.println(searchInsertOptimal(nums, 7));
    }
}
