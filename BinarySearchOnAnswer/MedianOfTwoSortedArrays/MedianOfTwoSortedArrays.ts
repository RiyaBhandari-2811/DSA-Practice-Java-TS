/**
 * Problem:
 * Find the median of two sorted arrays of different sizes.
 */

class MedianOfTwoSortedArrays {

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(m + n)
   * Space Complexity: O(m + n)
   *
   * Stable: Yes
   */
  static findMedianBrute(arr1: number[], arr2: number[]): number {
    const merged: number[] = [];
    let i = 0, j = 0;

    while (i < arr1.length && j < arr2.length) {
      if (arr1[i] <= arr2[j]) {
        merged.push(arr1[i++]);
      } else {
        merged.push(arr2[j++]);
      }
    }

    while (i < arr1.length) merged.push(arr1[i++]);
    while (j < arr2.length) merged.push(arr2[j++]);

    const len = merged.length;

    if (len % 2 === 1) {
      return merged[Math.floor(len / 2)];
    } else {
      return (merged[len / 2 - 1] + merged[len / 2]) / 2;
    }
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log(min(m, n)))
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static findMedianOptimal(arr1: number[], arr2: number[]): number {
    if (arr1.length > arr2.length) {
      return this.findMedianOptimal(arr2, arr1);
    }

    const m = arr1.length;
    const n = arr2.length;
    let low = 0, high = m;

    while (low <= high) {
      const cut1 = Math.floor((low + high) / 2);
      const cut2 = Math.floor((m + n + 1) / 2) - cut1;

      const l1 = cut1 === 0 ? Number.MIN_SAFE_INTEGER : arr1[cut1 - 1];
      const l2 = cut2 === 0 ? Number.MIN_SAFE_INTEGER : arr2[cut2 - 1];
      const r1 = cut1 === m ? Number.MAX_SAFE_INTEGER : arr1[cut1];
      const r2 = cut2 === n ? Number.MAX_SAFE_INTEGER : arr2[cut2];

      if (l1 <= r2 && l2 <= r1) {
        if ((m + n) % 2 === 0) {
          return (Math.max(l1, l2) + Math.min(r1, r2)) / 2;
        } else {
          return Math.max(l1, l2);
        }
      } else if (l1 > r2) {
        high = cut1 - 1;
      } else {
        low = cut1 + 1;
      }
    }

    return 0;
  }
}

// Test
const arr1 = [1, 3];
const arr2 = [2];

console.log(MedianOfTwoSortedArrays.findMedianBrute(arr1, arr2));
console.log(MedianOfTwoSortedArrays.findMedianOptimal(arr1, arr2));
