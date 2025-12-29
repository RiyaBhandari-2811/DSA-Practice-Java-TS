public class SearchInRotatedSortedArray {

    /**
     * Problem:
     * Search for a target in a rotated sorted array.
     */

    /**
     * Approach: Binary Search (Optimal)
     *
     * Idea:
     * - One half of the array is always sorted.
     * - Identify the sorted half.
     * - Check if the target lies in that half.
     * - Move left or right accordingly.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) return mid;

            // Left half is sorted
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        System.out.println(search(nums, 0));  // 4
        System.out.println(search(nums, 3));  // -1
    }
}
