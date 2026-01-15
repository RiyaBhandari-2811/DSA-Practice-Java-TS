/**
 * Problem:
 * Find any peak element in a 2D matrix.
 */

class PeakElement2D {

    /**
     * Approach 1: Brute Force
     *
     * Time Complexity: O(n * m)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    static findPeakBrute(mat: number[][]): number[] {
      const n = mat.length;
      const m = mat[0].length;
  
      for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
          const up = i > 0 ? mat[i - 1][j] : -1;
          const down = i < n - 1 ? mat[i + 1][j] : -1;
          const left = j > 0 ? mat[i][j - 1] : -1;
          const right = j < m - 1 ? mat[i][j + 1] : -1;
  
          if (
            mat[i][j] > up &&
            mat[i][j] > down &&
            mat[i][j] > left &&
            mat[i][j] > right
          ) {
            return [i, j];
          }
        }
      }
  
      return [-1, -1];
    }
  
    /**
     * Approach 2: Binary Search on Columns (Optimal)
     *
     * Time Complexity: O(n * log m)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    static findPeakOptimal(mat: number[][]): number[] {
      const n = mat.length;
      const m = mat[0].length;
  
      let low = 0;
      let high = m - 1;
  
      while (low <= high) {
        const mid = Math.floor((low + high) / 2);
  
        let maxRow = 0;
        for (let i = 0; i < n; i++) {
          if (mat[i][mid] > mat[maxRow][mid]) {
            maxRow = i;
          }
        }
  
        const left = mid > 0 ? mat[maxRow][mid - 1] : -1;
        const right = mid < m - 1 ? mat[maxRow][mid + 1] : -1;
  
        if (mat[maxRow][mid] > left && mat[maxRow][mid] > right) {
          return [maxRow, mid];
        } else if (left > mat[maxRow][mid]) {
          high = mid - 1;
        } else {
          low = mid + 1;
        }
      }
  
      return [-1, -1];
    }
  }
  
  // Test
  const mat1 = [
    [10, 20, 15],
    [21, 30, 14],
    [7, 16, 32]
  ];
  
  console.log(PeakElement2D.findPeakBrute(mat1));
  console.log(PeakElement2D.findPeakOptimal(mat1));
  