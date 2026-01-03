/**
 * Problem:
 * Find the Nth root of M.
 * If the Nth root is not an integer, return -1.
 */

class NthRoot {

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(M * N)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static nthRootBrute(N: number, M: number): number {
    for (let i = 1; i <= M; i++) {
      let power = 1;

      for (let j = 1; j <= N; j++) {
        power *= i;
        if (power > M) break;
      }

      if (power === M) return i;
    }

    return -1;
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log M * N)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static nthRootOptimal(N: number, M: number): number {
    let low = 1;
    let high = M;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);
      let power = 1;

      for (let i = 1; i <= N; i++) {
        power *= mid;
        if (power > M) break;
      }

      if (power === M) {
        return mid;
      } else if (power > M) {
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return -1;
  }
}

// Test
console.log(NthRoot.nthRootBrute(3, 27));
console.log(NthRoot.nthRootOptimal(3, 27));

console.log(NthRoot.nthRootBrute(2, 10));
console.log(NthRoot.nthRootOptimal(2, 10));
