/**
 * Problem:
 * Print the elements of a matrix in spiral order.
 *
 * Spiral order:
 * left -> right
 * top -> bottom
 * right -> left
 * bottom -> top
 */

class SpiralMatrix {
  /**
   * Approach: Boundary Traversal (Optimal)
   *
   * Idea:
   * - Maintain four pointers: top, bottom, left, right
   * - Traverse layer by layer in spiral form
   *
   * Time Complexity: O(m * n)
   * Space Complexity: O(m * n)
   *
   * Stable: Not Applicable
   */
  static spiralOrder(matrix: number[][]): number[] {
    const result: number[] = [];

    if (matrix.length === 0) return result;

    let top = 0;
    let bottom = matrix.length - 1;
    let left = 0;
    let right = matrix[0].length - 1;

    while (top <= bottom && left <= right) {
      // top row
      for (let j = left; j <= right; j++) {
        result.push(matrix[top][j]);
      }
      top++;

      // right column
      for (let i = top; i <= bottom; i++) {
        result.push(matrix[i][right]);
      }
      right--;

      // bottom row
      if (top <= bottom) {
        for (let j = right; j >= left; j--) {
          result.push(matrix[bottom][j]);
        }
        bottom--;
      }

      // left column
      if (left <= right) {
        for (let i = bottom; i >= top; i--) {
          result.push(matrix[i][left]);
        }
        left++;
      }
    }

    return result;
  }
}

// Test
const matrix = [
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9, 10, 11, 12],
];

console.log(SpiralMatrix.spiralOrder(matrix));
