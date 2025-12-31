/**
 * Problem:
 * Find how many times a sorted array has been rotated.
 */

class RotationCount {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static rotationCountBrute(arr: number[]): number {
    let min = arr[0];
    let index = 0;

    for (let i = 1; i < arr.length; i++) {
      if (arr[i] < min) {
        min = arr[i];
        index = i;
      }
    }

    return index;
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static rotationCountOptimal(arr: number[]): number {
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

    return low;
  }
}

// Test
const arr = [4, 5, 6, 7, 0, 1, 2];
console.log(RotationCount.rotationCountBrute(arr));
console.log(RotationCount.rotationCountOptimal(arr));
