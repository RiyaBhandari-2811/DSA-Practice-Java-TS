/**
 * Problem:
 * Allocate books to m students such that the
 * maximum pages assigned is minimized.
 */

class AllocateBooks {
  /**
   * Helper Method:
   * Checks feasibility for given maxPages
   */
  private static canAllocate(
    arr: number[],
    m: number,
    maxPages: number
  ): boolean {
    let students = 1;
    let pages = 0;

    for (const book of arr) {
      if (pages + book > maxPages) {
        students++;
        pages = book;
      } else {
        pages += book;
      }
    }

    return students <= m;
  }

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O((sum - max) * n)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static allocateBooksBrute(arr: number[], m: number): number {
    if (m > arr.length) return -1;

    let max = 0;
    let sum = 0;

    for (const pages of arr) {
      max = Math.max(max, pages);
      sum += pages;
    }

    for (let maxPages = max; maxPages <= sum; maxPages++) {
      if (this.canAllocate(arr, m, maxPages)) {
        return maxPages;
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
  static allocateBooksOptimal(arr: number[], m: number): number {
    if (m > arr.length) return -1;

    let low = 0;
    let high = 0;

    for (const pages of arr) {
      low = Math.max(low, pages);
      high += pages;
    }

    let ans = high;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (this.canAllocate(arr, m, mid)) {
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
const arr = [12, 34, 67, 90];
const m = 2;

console.log(AllocateBooks.allocateBooksBrute(arr, m));
console.log(AllocateBooks.allocateBooksOptimal(arr, m));
