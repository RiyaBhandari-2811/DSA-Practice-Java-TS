/**
 * Problem:
 * Find the minimum eating speed k so that Koko
 * can eat all bananas within h hours.
 */

class KokoEatingBananas {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(maxPile * n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static minEatingSpeedBrute(piles: number[], h: number): number {
    let maxPile = Math.max(...piles);

    for (let k = 1; k <= maxPile; k++) {
      let hours = 0;

      for (const pile of piles) {
        hours += Math.ceil(pile / k);
      }

      if (hours <= h) return k;
    }

    return -1;
  }

  /**
   * Approach 2: Binary Search on Answer (Optimal)
   *
   * Time Complexity: O(n * log(maxPile))
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static minEatingSpeedOptimal(piles: number[], h: number): number {
    let low = 1;
    let high = Math.max(...piles);
    let ans = high;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);
      let hours = 0;

      for (const pile of piles) {
        hours += Math.ceil(pile / mid);
      }

      if (hours <= h) {
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
const piles = [3, 6, 7, 11];
const h = 8;

console.log(KokoEatingBananas.minEatingSpeedBrute(piles, h));
console.log(KokoEatingBananas.minEatingSpeedOptimal(piles, h));
