/**
 * Problem:
 * Find the minimum element in a rotated sorted array (no duplicates).
 */

class MinInRotatedSortedArray {
  /**
   * Approach: Binary Search (Optimal)
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static findMin(arr: number[]): number {
    let low = 0;
    let high = arr.length - 1;

    while (low < high) {
      const mid = Math.floor((low + high) / 2);

      if (arr[mid] > arr[high]) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }

    return arr[low];
  }
}

// Test
const arr = [4, 5, 6, 7, 0, 1, 2];
console.log(MinInRotatedSortedArray.findMin(arr));
