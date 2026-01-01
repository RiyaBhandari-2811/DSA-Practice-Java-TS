/**
 * Problem:
 * Find the index of a peak element in the array.
 *
 * A peak element is greater than its neighbors.
 * Boundary elements consider outside values as -Infinity.
 */

class PeakElement {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static findPeakBrute(arr: number[]): number {
    const n = arr.length;

    if (n === 1) return 0;
    if (arr[0] > arr[1]) return 0;
    if (arr[n - 1] > arr[n - 2]) return n - 1;

    for (let i = 1; i < n - 1; i++) {
      if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static findPeakOptimal(arr: number[]): number {
    let low = 0;
    let high = arr.length - 1;

    while (low < high) {
      const mid = Math.floor((low + high) / 2);

      if (arr[mid] > arr[mid + 1]) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }

    return low;
  }
}

// Test
const arr = [1, 2, 3, 1];
console.log(PeakElement.findPeakBrute(arr));
console.log(PeakElement.findPeakOptimal(arr));
