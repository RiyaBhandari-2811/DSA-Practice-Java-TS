/**
 * Problem:
 * Find the square root of a number.
 * Return the floor value.
 */

class SquareRoot {

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(√n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static sqrtBrute(n: number): number {
    if (n === 0 || n === 1) return n;

    let i = 1;
    while (i * i <= n) {
      i++;
    }

    return i - 1;
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static sqrtOptimal(n: number): number {
    if (n === 0 || n === 1) return n;

    let low = 1;
    let high = n;
    let ans = 0;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (mid * mid <= n) {
        ans = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return ans; // or high
  }
}

// Test
console.log(SquareRoot.sqrtBrute(16));
console.log(SquareRoot.sqrtOptimal(16));

console.log(SquareRoot.sqrtBrute(10));
console.log(SquareRoot.sqrtOptimal(10));
