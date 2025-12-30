
public class SearchInRotatedSortedArrayII {

    /**
     * Problem: Search a target in a rotated sorted array that may contain
     * duplicates.
     */
    /**
     * Approach: Modified Binary Search
     *
     * Idea: - When duplicates exist, arr[low] == arr[mid] == arr[high] makes it
     * unclear which side is sorted. - In that case, shrink the search space.
     *
     * Time Complexity: - Average: O(log n) - Worst case (many duplicates): O(n)
     *
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return true;
            }

            // Handle duplicates
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
            } // Left half is sorted
            else if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } // Right half is sorted
            else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 0, 0, 1, 2};

        System.out.println(search(nums, 0)); // true
        System.out.println(search(nums, 3)); // false
    }
}
