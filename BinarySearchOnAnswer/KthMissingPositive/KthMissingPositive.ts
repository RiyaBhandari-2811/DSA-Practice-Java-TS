/**
 * Problem:
 * Find the kth missing positive number from a strictly increasing array.
 */

class KthMissingPositive {

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n + k)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static findKthMissingBrute(arr: number[], k: number): number {
    let i = 0;
    let current = 1;

    while (true) {
      if (i < arr.length && arr[i] === current) {
        i++;
      } else {
        k--;
        if (k === 0) return current;
      }
      current++;
    }
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static findKthMissingOptimal(arr: number[], k: number): number {
    let low = 0;
    let high = arr.length - 1;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);
      const missing = arr[mid] - (mid + 1);

      if (missing < k) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return low + k;
  }
}

// Test
const vec = [2, 3, 4, 7, 11];
const k = 5;

console.log(KthMissingPositive.findKthMissingBrute(vec, k));
console.log(KthMissingPositive.findKthMissingOptimal(vec, k));
