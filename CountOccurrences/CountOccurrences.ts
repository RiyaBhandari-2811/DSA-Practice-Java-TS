/**
 * Problem:
 * Count the number of times a given number appears in a sorted array.
 */

class CountOccurrences {

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static countBrute(arr: number[], x: number): number {
    let count = 0;

    for (const num of arr) {
      if (num === x) count++;
    }

    return count;
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static countOptimal(arr: number[], x: number): number {
    const first = this.findFirst(arr, x);
    if (first === -1) return 0;

    const last = this.findLast(arr, x);
    return last - first + 1;
  }

  private static findFirst(arr: number[], x: number): number {
    let low = 0, high = arr.length - 1;
    let ans = -1;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (arr[mid] === x) {
        ans = mid;
        high = mid - 1;
      } else if (arr[mid] < x) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return ans;
  }

  private static findLast(arr: number[], x: number): number {
    let low = 0, high = arr.length - 1;
    let ans = -1;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (arr[mid] === x) {
        ans = mid;
        low = mid + 1;
      } else if (arr[mid] < x) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return ans;
  }
}

// Test
const arr = [1, 2, 2, 2, 3, 4];
console.log(CountOccurrences.countBrute(arr, 2));
console.log(CountOccurrences.countOptimal(arr, 2));
