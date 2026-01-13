/**
 * Problem:
 * Search for a target in a sorted 2D matrix.
 */

class SearchIn2DMatrix {

  /**
   * Approach 1: Brute Force
   *
   * Time Complexity: O(n * m)
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static searchBrute(mat: number[][], target: number): boolean {
    for (let i = 0; i < mat.length; i++) {
      for (let j = 0; j < mat[0].length; j++) {
        if (mat[i][j] === target) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Approach 2: Binary Search (Optimal)
   *
   * Time Complexity: O(log(n * m))
   * Space Complexity: O(1)
   *
   * Stable: Yes
   */
  static searchOptimal(mat: number[][], target: number): boolean {
    const n = mat.length;
    const m = mat[0].length;

    let low = 0;
    let high = n * m - 1;

    while (low <= high) {
      const mid = Math.floor((low + high) / 2);
      const row = Math.floor(mid / m);
      const col = mid % m;

      if (mat[row][col] === target) {
        return true;
      } else if (mat[row][col] < target) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }

    return false;
  }
}

// Test
const mat1 = [
  [1, 3, 5, 7],
  [10, 11, 16, 20],
  [23, 30, 34, 60]
];

console.log(SearchIn2DMatrix.searchBrute(mat1, 3));
console.log(SearchIn2DMatrix.searchOptimal(mat1, 3));
