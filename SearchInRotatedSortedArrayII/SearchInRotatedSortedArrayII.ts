/**
 * Problem:
 * Search a target in a rotated sorted array that may contain duplicates.
 */

class SearchInRotatedSortedArrayII {
  /**
   * Approach: Modified Binary Search
   *
   * Time Complexity:
   * - Average: O(log n)
   * - Worst case: O(n)
   *
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static search(nums: number[], target: number): boolean {
    let low = 0;
    let high = nums.length - 1;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (nums[mid] === target) return true;

      // Handle duplicates
      if (nums[low] === nums[mid] && nums[mid] === nums[high]) {
        low++;
        high--;
      }
      // Left half is sorted
      else if (nums[low] <= nums[mid]) {
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

    return false;
  }
}

// Test
const nums = [2, 5, 6, 0, 0, 1, 2];
console.log(SearchInRotatedSortedArrayII.search(nums, 0));
console.log(SearchInRotatedSortedArrayII.search(nums, 3));
