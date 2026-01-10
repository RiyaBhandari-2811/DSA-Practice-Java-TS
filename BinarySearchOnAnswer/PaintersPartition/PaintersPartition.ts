/**
 * Problem:
 * Find the minimum time required to paint all boards
 * using K painters, with contiguous allocation.
 */

class PaintersPartition {
  /**
   * Helper Method:
   * Checks feasibility for given maxTime
   */
  private static canPaint(arr: number[], K: number, maxTime: number): boolean {
    let painters = 1;
    let time = 0;

    for (const board of arr) {
      if (time + board > maxTime) {
        painters++;
        time = board;
      } else {
        time += board;
      }
    }

    return painters <= K;
  }

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O((sum - max) * n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static paintersPartitionBrute(arr: number[], K: number): number {
    if (K > arr.length) return -1;

    let max = 0;
    let sum = 0;

    for (const board of arr) {
      max = Math.max(max, board);
      sum += board;
    }

    for (let time = max; time <= sum; time++) {
      if (this.canPaint(arr, K, time)) {
        return time;
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
  static paintersPartitionOptimal(arr: number[], K: number): number {
    if (K > arr.length) return -1;

    let low = 0;
    let high = 0;

    for (const board of arr) {
      low = Math.max(low, board);
      high += board;
    }

    let ans = high;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (this.canPaint(arr, K, mid)) {
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
const arr1 = [10, 20, 30, 40];
const K = 2;

console.log(PaintersPartition.paintersPartitionBrute(arr1, K));
console.log(PaintersPartition.paintersPartitionOptimal(arr1, K));
