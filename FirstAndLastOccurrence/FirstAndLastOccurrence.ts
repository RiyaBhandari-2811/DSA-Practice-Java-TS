/**
 * Problem:
 * Find the first and last occurrence of a target value in a sorted array.
 */

class FirstAndLastOccurrence {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static firstLastBrute(arr: number[], x: number): number[] {
    let first = -1;
    let last = -1;

    for (let i = 0; i < arr.length; i++) {
      if (arr[i] === x) {
        if (first === -1) first = i;
        last = i;
      }
    }

    return [first, last];
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static firstLastOptimal(arr: number[], x: number): number[] {
    const first = this.findFirst(arr, x);
    const last = this.findLast(arr, x);
    return [first, last];
  }

  private static findFirst(arr: number[], x: number): number {
    let low = 0,
      high = arr.length - 1;
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
    let low = 0,
      high = arr.length - 1;
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
console.log(FirstAndLastOccurrence.firstLastOptimal(arr, 2));
