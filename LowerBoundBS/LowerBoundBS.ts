/**
 * Problem:
 * Find the smallest index such that arr[index] >= x.
 * If no such index exists, return n.
 */

class LowerBoundBS {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static lowerBoundBrute(arr: number[], x: number): number {
    for (let i = 0; i < arr.length; i++) {
      if (arr[i] >= x) {
        return i;
      }
    }
    return arr.length;
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static lowerBoundOptimal(arr: number[], x: number): number {
    let low = 0;
    let high = arr.length - 1;
    let ans = arr.length;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (arr[mid] >= x) {
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
const arr = [1, 3, 5, 6];

console.log(LowerBoundBS.lowerBoundBrute(arr, 5));
console.log(LowerBoundBS.lowerBoundOptimal(arr, 5));

console.log(LowerBoundBS.lowerBoundBrute(arr, 7));
console.log(LowerBoundBS.lowerBoundOptimal(arr, 7));
