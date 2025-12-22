
import java.util.Arrays;

public class SetMatrixZeroes {

    /**
     * Problem: Given an m x n integer matrix, if an element is 0, set its
     * entire row and column to 0.
     *
     * You must do it in place.
     *
     * Example: Input: [ [1, 1, 1], [1, 0, 1], [1, 1, 1] ]
     *
     * Output: [ [1, 0, 1], [0, 0, 0], [1, 0, 1] ]
     */
    /**
     * Approach 1: Brute Force
     *
     * Idea: - Traverse the matrix. - Whenever a zero is found, mark its entire
     * row and column as -1 (except existing zeros). - In the final pass,
     * convert all -1 values to 0.
     *
     * Time Complexity: O((m * n) * (m + n)) Space Complexity: O(1)
     *
     * Stable: Not Applicable - Matrix structure is modified.
     */
    public static void setZeroesBrute(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    for (int col = 0; col < n; col++) {
                        if (matrix[i][col] != 0) {
                            matrix[i][col] = -1;
                        }
                    }
                    for (int row = 0; row < m; row++) {
                        if (matrix[row][j] != 0) {
                            matrix[row][j] = -1;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * Approach 2: Better (Using Extra Space)
     *
     * Idea: - Use two arrays row[] and col[] to track which rows and columns
     * need to be zeroed. - Traverse matrix and mark row[i] and col[j] when
     * matrix[i][j] == 0. - Traverse again and set matrix[i][j] = 0 if row[i] or
     * col[j] is marked.
     *
     * Time Complexity: O(m * n) Space Complexity: O(m + n)
     *
     * Stable: Not Applicable
     */
    public static void setZeroesBetter(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * Approach 3: Optimal (In-Place)
     *
     * Idea: - Use first row and first column as markers. - Use an extra
     * variable col0 to track whether first column should be zero. - First pass
     * marks rows and columns. - Second pass updates the matrix based on
     * markers. - Finally update first row and first column.
     *
     * Time Complexity: O(m * n) Space Complexity: O(1)
     *
     * Stable: Not Applicable
     */
    public static void setZeroesOptimal(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int col0 = 1;

        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                col0 = 0;
            }

            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0 == 0) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        setZeroesOptimal(matrix);

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
