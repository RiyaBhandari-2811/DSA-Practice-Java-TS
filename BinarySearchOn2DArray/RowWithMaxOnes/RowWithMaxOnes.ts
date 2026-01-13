/**
 * Problem:
 * Find the index of the row with maximum number of 1s
 * in a binary matrix with sorted rows.
 */

class RowWithMaxOnes {
  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n * m)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static rowWithMaxOnesBrute(mat: number[][]): number {
    let maxOnes = 0;
    let rowIndex = -1;

    for (let i = 0; i < mat.length; i++) {
      let count = 0;
      for (let j = 0; j < mat[0].length; j++) {
        if (mat[i][j] === 1) count++;
      }

      if (count > maxOnes) {
        maxOnes = count;
        rowIndex = i;
      }
    }

    return rowIndex;
  }

  /**
   * Helper Method:
   * Binary search to find first 1
   */
  private static firstOneIndex(row: number[]): number {
    let low = 0,
      high = row.length - 1;
    let ans = -1;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);

      if (row[mid] === 1) {
        ans = mid;
        high = mid - 1;
      } else {
        low = mid + 1;
      }
    }

    return ans;
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(n * log m)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static rowWithMaxOnesOptimal(mat: number[][]): number {
    let maxOnes = 0;
    let rowIndex = -1;
    const m = mat[0].length;

    for (let i = 0; i < mat.length; i++) {
      const firstOne = this.firstOneIndex(mat[i]);
      if (firstOne !== -1) {
        const count = m - firstOne;
        if (count > maxOnes) {
          maxOnes = count;
          rowIndex = i;
        }
      }
    }

    return rowIndex;
  }
}

// Test
const mat = [
  [0, 0, 1, 1],
  [0, 1, 1, 1],
  [0, 0, 0, 1],
];

console.log(RowWithMaxOnes.rowWithMaxOnesBrute(mat));
console.log(RowWithMaxOnes.rowWithMaxOnesOptimal(mat));
