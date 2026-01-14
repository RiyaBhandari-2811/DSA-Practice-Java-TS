/**
 * Problem:
 * Search for a target in a matrix where
 * rows and columns are sorted.
 */

class SearchRowColSortedMatrix {

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
     * Approach 2: Staircase Search (Optimal)
     *
     * Time Complexity: O(n + m)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    static searchOptimal(mat: number[][], target: number): boolean {
      const n = mat.length;
      const m = mat[0].length;
  
      let row = 0;
      let col = m - 1;
  
      while (row < n && col >= 0) {
        if (mat[row][col] === target) {
          return true;
        } else if (mat[row][col] > target) {
          col--;
        } else {
          row++;
        }
      }
  
      return false;
    }
  }
  
  // Test
  const mat = [
    [1, 4, 7, 11],
    [2, 5, 8, 12],
    [3, 6, 9, 16],
    [10, 13, 14, 17]
  ];
  
  console.log(SearchRowColSortedMatrix.searchBrute(mat, 5));
  console.log(SearchRowColSortedMatrix.searchOptimal(mat, 5));
  