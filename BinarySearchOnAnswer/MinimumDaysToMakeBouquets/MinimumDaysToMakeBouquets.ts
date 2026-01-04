/**
 * Problem:
 * Find the minimum number of days to make m bouquets
 * using k adjacent bloomed roses.
 */

class MinimumDaysToMakeBouquets {
  /**
   * Helper Method:
   * Checks if m bouquets can be formed within given days
   */
  private static canMake(
    arr: number[],
    m: number,
    k: number,
    days: number
  ): boolean {
    let bouquets = 0;
    let flowers = 0;

    for (const bloom of arr) {
      if (bloom <= days) {
        flowers++;
      } else {
        bouquets += Math.floor(flowers / k);
        flowers = 0;
      }
    }

    bouquets += Math.floor(flowers / k);

    return bouquets >= m;
  }

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O((maxDay - minDay) * n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static minDaysBrute(arr: number[], m: number, k: number): number {
    if (m * k > arr.length) return -1;

    let minDay = Math.min(...arr);
    let maxDay = Math.max(...arr);

    for (let day = minDay; day <= maxDay; day++) {
      if (this.canMake(arr, m, k, day)) {
        return day;
      }
    }

    return -1;
  }

  /**
   * Approach 2: Binary Search on Answer (Optimal)
   *
   * Time Complexity: O(n * log(maxDay))
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static minDaysOptimal(arr: number[], m: number, k: number): number {
    if (m * k > arr.length) return -1;

    let low = Math.min(...arr);
    let high = Math.max(...arr);
    let ans = -1;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (this.canMake(arr, m, k, mid)) {
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
const arr = [1, 10, 3, 10, 2];
const m = 3;
const k = 1;

console.log(MinimumDaysToMakeBouquets.minDaysBrute(arr, m, k));
console.log(MinimumDaysToMakeBouquets.minDaysOptimal(arr, m, k));
