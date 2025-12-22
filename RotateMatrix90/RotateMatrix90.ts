/**
 * Problem:
 * Given an n x n matrix, rotate the matrix by 90 degrees clockwise.
 * The rotation must be done in place.
 *
 * Example:
 * Input:
 * [
 *   [1, 2, 3],
 *   [4, 5, 6],
 *   [7, 8, 9]
 * ]
 *
 * Output:
 * [
 *   [7, 4, 1],
 *   [8, 5, 2],
 *   [9, 6, 3]
 * ]
 */

class RotateMatrix90 {
  /**
   * Approach 1: Brute Force (Using Extra Matrix)
   *
   * Idea:
   * - Create a new matrix.
   * - Place matrix[i][j] at position [j][n - 1 - i].
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(n^2)
   *
   * Stable: Not Applicable
   */
  static rotateBrute(matrix: number[][]): number[][] {
    const n = matrix.length;
    const result: number[][] = Array.from({ length: n }, () =>
      new Array(n).fill(0)
    );

    for (let i = 0; i < n; i++) {
      for (let j = 0; j < n; j++) {
        result[j][n - 1 - i] = matrix[i][j];
      }
    }

    return result;
  }

  /**
   * Approach 2: Better (Transpose + Reverse Rows)
   *
   * Idea:
   * - Transpose the matrix (swap matrix[i][j] with matrix[j][i]).
   * - Reverse each row.
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(1)
   *
   * Stable: Not Applicable
   */
  static rotateBetter(matrix: number[][]): void {
    const n = matrix.length;

    // Transpose
    for (let i = 0; i < n; i++) {
      for (let j = i + 1; j < n; j++) {
        [matrix[i][j], matrix[j][i]] = [matrix[j][i], matrix[i][j]];
      }
    }

    // Reverse each row
    for (let i = 0; i < n; i++) {
      matrix[i].reverse();
    }
  }

  /**
   * Approach 3: Optimal (Layer by Layer Rotation)
   *
   * Idea:
   * - Rotate the matrix layer by layer.
   * - For each layer, swap four elements at a time.
   *
   * Time Complexity: O(n^2)
   * Space Complexity: O(1)
   *
   * Stable: Not Applicable
   */
  static rotateOptimal(matrix: number[][]): void {
    const n = matrix.length;

    for (let layer = 0; layer < Math.floor(n / 2); layer++) {
      const first = layer;
      const last = n - 1 - layer;

      for (let i = first; i < last; i++) {
        const offset = i - first;
        const top = matrix[first][i];

        // left -> top
        matrix[first][i] = matrix[last - offset][first];

        // bottom -> left
        matrix[last - offset][first] = matrix[last][last - offset];

        // right -> bottom
        matrix[last][last - offset] = matrix[i][last];

        // top -> right
        matrix[i][last] = top;
      }
    }
  }
}

// Test
const matrix = [
  [1, 2, 3],
  [4, 5, 6],
  [7, 8, 9],
];

RotateMatrix90.rotateBetter(matrix);
console.log(matrix);
