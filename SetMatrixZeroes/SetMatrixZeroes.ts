/**
 * Problem:
 * Given an m x n matrix, if an element is 0,
 * set its entire row and column to 0.
 *
 * The operation must be done in place.
 *
 * Example:
 * Input:
 * [
 *   [1, 1, 1],
 *   [1, 0, 1],
 *   [1, 1, 1]
 * ]
 *
 * Output:
 * [
 *   [1, 0, 1],
 *   [0, 0, 0],
 *   [1, 0, 1]
 * ]
 */

class SetMatrixZeroes {
  /**
   * Approach 1: Brute Force
   *
   * Idea:
   * - Traverse the matrix.
   * - When a zero is found, mark all elements in its row and column as -1
   *   except the existing zeros.
   * - In a second pass, convert all -1 values to 0.
   *
   * Time Complexity: O((m * n) * (m + n))
   * Space Complexity: O(1)
   *
   * Stable: Not Applicable
   */
  static setZeroesBrute(matrix: number[][]): void {
    const m = matrix.length;
    const n = matrix[0].length;

    for (let i = 0; i < m; i++) {
      for (let j = 0; j < n; j++) {
        if (matrix[i][j] === 0) {
          for (let col = 0; col < n; col++) {
            if (matrix[i][col] !== 0) matrix[i][col] = -1;
          }
          for (let row = 0; row < m; row++) {
            if (matrix[row][j] !== 0) matrix[row][j] = -1;
          }
        }
      }
    }

    for (let i = 0; i < m; i++) {
      for (let j = 0; j < n; j++) {
        if (matrix[i][j] === -1) matrix[i][j] = 0;
      }
    }
  }

  /**
   * Approach 2: Better (Using Extra Space)
   *
   * Idea:
   * - Use two arrays row[] and col[].
   * - Mark row[i] and col[j] whenever matrix[i][j] is zero.
   * - In the second pass, set matrix[i][j] to zero
   *   if row[i] or col[j] is marked.
   *
   * Time Complexity: O(m * n)
   * Space Complexity: O(m + n)
   *
   * Stable: Not Applicable
   */
  static setZeroesBetter(matrix: number[][]): void {
    const m = matrix.length;
    const n = matrix[0].length;

    const row: boolean[] = new Array(m).fill(false);
    const col: boolean[] = new Array(n).fill(false);

    for (let i = 0; i < m; i++) {
      for (let j = 0; j < n; j++) {
        if (matrix[i][j] === 0) {
          row[i] = true;
          col[j] = true;
        }
      }
    }

    for (let i = 0; i < m; i++) {
      for (let j = 0; j < n; j++) {
        if (row[i] || col[j]) {
          matrix[i][j] = 0;
        }
      }
    }
  }

  /**
   * Approach 3: Optimal (In-Place)
   *
   * Idea:
   * - Use the first row and first column as markers.
   * - Use an extra variable col0 to track whether the first column
   *   needs to be zeroed.
   * - First pass sets markers.
   * - Second pass updates cells using markers.
   * - Finally update the first column if required.
   *
   * Time Complexity: O(m * n)
   * Space Complexity: O(1)
   *
   * Stable: Not Applicable
   */
  static setZeroesOptimal(matrix: number[][]): void {
    const m = matrix.length;
    const n = matrix[0].length;

    let col0 = 1;

    for (let i = 0; i < m; i++) {
      if (matrix[i][0] === 0) col0 = 0;

      for (let j = 1; j < n; j++) {
        if (matrix[i][j] === 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }

    for (let i = m - 1; i >= 0; i--) {
      for (let j = n - 1; j >= 1; j--) {
        if (matrix[i][0] === 0 || matrix[0][j] === 0) {
          matrix[i][j] = 0;
        }
      }
      if (col0 === 0) {
        matrix[i][0] = 0;
      }
    }
  }
}

// Test
const matrix = [
  [1, 1, 1],
  [1, 0, 1],
  [1, 1, 1],
];

SetMatrixZeroes.setZeroesOptimal(matrix);
console.log(matrix);
