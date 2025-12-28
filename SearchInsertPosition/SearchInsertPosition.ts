/**
 * Problem:
 * Find the index of target in a sorted array.
 * If not found, return the index where it should be inserted.
 */

class SearchInsertPosition {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static searchInsertBrute(nums: number[], target: number): number {
    for (let i = 0; i < nums.length; i++) {
      if (nums[i] >= target) {
        return i;
      }
    }
    return nums.length;
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static searchInsertOptimal(nums: number[], target: number): number {
    let low = 0;
    let high = nums.length - 1;
    let ans = nums.length;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (nums[mid] >= target) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return ans;
  }
}

// Test
const nums = [1, 3, 5, 6];

console.log(SearchInsertPosition.searchInsertBrute(nums, 5));
console.log(SearchInsertPosition.searchInsertOptimal(nums, 5));

console.log(SearchInsertPosition.searchInsertBrute(nums, 2));
console.log(SearchInsertPosition.searchInsertOptimal(nums, 2));

console.log(SearchInsertPosition.searchInsertBrute(nums, 7));
console.log(SearchInsertPosition.searchInsertOptimal(nums, 7));
