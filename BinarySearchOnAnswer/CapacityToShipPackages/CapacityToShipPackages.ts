/**
 * Problem:
 * Find the minimum ship capacity to ship all packages
 * within D days.
 */

class CapacityToShipPackages {
  /**
   * Helper Method:
   * Checks feasibility for given capacity
   */
  private static canShip(
    weights: number[],
    D: number,
    capacity: number
  ): boolean {
    let days = 1;
    let load = 0;

    for (const w of weights) {
      if (load + w > capacity) {
        days++;
        load = w;
      } else {
        load += w;
      }
    }

    return days <= D;
  }

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O((sum - max) * n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static shipWithinDaysBrute(weights: number[], D: number): number {
    let max = 0;
    let sum = 0;

    for (const w of weights) {
      max = Math.max(max, w);
      sum += w;
    }

    for (let cap = max; cap <= sum; cap++) {
      if (this.canShip(weights, D, cap)) {
        return cap;
      }
    }

    return -1;
  }

  /**
   * Approach 2: Binary Search on Answer (Optimal)
   *
   * Time Complexity: O(n * log(sum))
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static shipWithinDaysOptimal(weights: number[], D: number): number {
    let low = 0;
    let high = 0;

    for (const w of weights) {
      low = Math.max(low, w);
      high += w;
    }

    let ans = high;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (this.canShip(weights, D, mid)) {
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
const weights = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
const D = 5;

console.log(CapacityToShipPackages.shipWithinDaysBrute(weights, D));
console.log(CapacityToShipPackages.shipWithinDaysOptimal(weights, D));
