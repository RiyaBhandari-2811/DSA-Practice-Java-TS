/**
 * Problem:
 * Split array into K contiguous subarrays
 * such that the largest subarray sum is minimized.
 */

class SplitArrayLargestSum {

  /**
   * Helper Method:
   * Checks feasibility for given maxSum
   */
  private static canSplit(arr: number[], K: number, maxSum: number): boolean {
    let subarrays = 1;
    let currentSum = 0;

    for (const num of arr) {
      if (currentSum + num > maxSum) {
        subarrays++;
        currentSum = num;
      } else {
        currentSum += num;
      }
    }

    return subarrays <= K;
  }

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O((sum - max) * n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static splitArrayBrute(arr: number[], K: number): number {
    let max = 0;
    let sum = 0;

    for (const num of arr) {
      max = Math.max(max, num);
      sum += num;
    }

    for (let maxSum = max; maxSum <= sum; maxSum++) {
      if (this.canSplit(arr, K, maxSum)) {
        return maxSum;
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
  static splitArrayOptimal(arr: number[], K: number): number {
    let low = 0;
    let high = 0;

    for (const num of arr) {
      low = Math.max(low, num);
      high += num;
    }

    let ans = high;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (this.canSplit(arr, K, mid)) {
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
const A = [7, 2, 5, 10, 8];
const K = 2;

console.log(SplitArrayLargestSum.splitArrayBrute(A, K));
console.log(SplitArrayLargestSum.splitArrayOptimal(A, K));
