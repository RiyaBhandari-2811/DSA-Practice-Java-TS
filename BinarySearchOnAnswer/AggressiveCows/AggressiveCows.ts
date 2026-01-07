/**
 * Problem:
 * Place k cows in stalls such that the minimum distance
 * between any two cows is maximized.
 */

class AggressiveCows {

  /**
   * Helper Method:
   * Checks feasibility for given distance
   */
  private static canPlace(arr: number[], k: number, dist: number): boolean {
    let cows = 1;
    let lastPos = arr[0];

    for (let i = 1; i < arr.length; i++) {
      if (arr[i] - lastPos >= dist) {
        cows++;
        lastPos = arr[i];
      }
      if (cows === k) return true;
    }

    return false;
  }

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n * maxDistance)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static aggressiveCowsBrute(arr: number[], k: number): number {
    arr.sort((a, b) => a - b);

    const maxDist = arr[arr.length - 1] - arr[0];

    for (let d = 1; d <= maxDist; d++) {
      if (!this.canPlace(arr, k, d)) {
        return d - 1;
      }
    }

    return maxDist;
  }

  /**
   * Approach 2: Binary Search on Answer (Optimal)
   *
   * Time Complexity: O(n * log(maxDistance))
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static aggressiveCowsOptimal(arr: number[], k: number): number {
    arr.sort((a, b) => a - b);

    let low = 1;
    let high = arr[arr.length - 1] - arr[0];
    let ans = 0;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (this.canPlace(arr, k, mid)) {
        ans = mid;
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return ans;
  }
}

// Test
const arr = [1, 2, 4, 8, 9];
const k = 3;

console.log(AggressiveCows.aggressiveCowsBrute(arr, k));
console.log(AggressiveCows.aggressiveCowsOptimal(arr, k));
