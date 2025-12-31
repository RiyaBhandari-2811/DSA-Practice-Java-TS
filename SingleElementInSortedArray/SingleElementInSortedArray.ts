/**
 * Problem:
 * Find the element that appears only once in a sorted array.
 */

class SingleElementInSortedArray {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static singleElementBrute(arr: number[]): number {
    const n = arr.length;

    if (n === 1) return arr[0];
    if (arr[0] !== arr[1]) return arr[0];
    if (arr[n - 1] !== arr[n - 2]) return arr[n - 1];

    for (let i = 1; i < n - 1; i++) {
      if (arr[i] !== arr[i - 1] && arr[i] !== arr[i + 1]) {
        return arr[i];
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
  static singleElementOptimal(arr: number[]): number {
    let low = 0;
    let high = arr.length - 1;

    while (low < high) {
      let mid = Math.floor((low + high) / 2);

      // Make mid even
      if (mid % 2 === 1) mid--;

      if (arr[mid] === arr[mid + 1]) {
        low = mid + 2;
      } else {
        high = mid;
      }
    }

    return arr[low];
  }
}

// Test
const nums = [1, 1, 2, 3, 3, 4, 4, 8, 8];
console.log(SingleElementInSortedArray.singleElementBrute(nums));
console.log(SingleElementInSortedArray.singleElementOptimal(nums));
