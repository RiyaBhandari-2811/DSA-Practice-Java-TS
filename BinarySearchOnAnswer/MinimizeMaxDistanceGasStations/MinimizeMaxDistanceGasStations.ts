/**
 * Problem:
 * Minimize the maximum distance between adjacent gas stations
 * after adding k new stations.
 */

class MinimizeMaxDistanceGasStations {
  /**
   * Helper Method:
   * Calculates required new stations for given distance
   */
  private static requiredStations(arr: number[], dist: number): number {
    let count = 0;

    for (let i = 1; i < arr.length; i++) {
      const gap = arr[i] - arr[i - 1];
      count += Math.floor(gap / dist);
    }

    return count;
  }

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: Very high
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static minimizeMaxDistanceBrute(arr: number[], k: number): number {
    let low = 0;
    let high = arr[arr.length - 1] - arr[0];
    const step = 0.0001;

    for (let d = high; d >= low; d -= step) {
      if (this.requiredStations(arr, d) > k) {
        return d + step;
      }
    }

    return low;
  }

  /**
   * Approach 2: Binary Search on Answer (Optimal)
   *
   * Time Complexity: O(n * log(range / precision))
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static minimizeMaxDistanceOptimal(arr: number[], k: number): number {
    let low = 0;
    let high = 0;

    for (let i = 1; i < arr.length; i++) {
      high = Math.max(high, arr[i] - arr[i - 1]);
    }

    const eps = 1e-6;

    while (high - low > eps) {
      const mid = (low + high) / 2;

      if (this.requiredStations(arr, mid) > k) {
        low = mid;
      } else {
        high = mid;
      }
    }

    return high;
  }
}

// Test
const arr = [1, 2, 3, 4, 5];
const k = 4;

console.log(MinimizeMaxDistanceGasStations.minimizeMaxDistanceOptimal(arr, k));
