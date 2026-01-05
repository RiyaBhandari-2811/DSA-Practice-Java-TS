/**
 * Problem:
 * Find the smallest divisor such that
 * sum of ceil(arr[i] / divisor) <= limit.
 */

class SmallestDivisor {

  /**
   * Helper Method:
   * Computes sum for a given divisor
   */
  private static computeSum(arr: number[], divisor: number): number {
    let sum = 0;
    for (const num of arr) {
      sum += Math.ceil(num / divisor);
    }
    return sum;
  }

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(maxElement * n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static smallestDivisorBrute(arr: number[], limit: number): number {
    const max = Math.max(...arr);

    for (let d = 1; d <= max; d++) {
      if (this.computeSum(arr, d) <= limit) {
        return d;
      }
    }

    return -1;
  }

  /**
   * Approach 2: Binary Search on Answer (Optimal)
   *
   * Time Complexity: O(n * log(maxElement))
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static smallestDivisorOptimal(arr: number[], limit: number): number {
    let low = 1;
    let high = Math.max(...arr);
    let ans = high;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (this.computeSum(arr, mid) <= limit) {
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
const arr = [1, 2, 5, 9];
const limit = 6;

console.log(SmallestDivisor.smallestDivisorBrute(arr, limit));
console.log(SmallestDivisor.smallestDivisorOptimal(arr, limit));
