
import java.util.Arrays;

public class RotateMatrix90 {

    /**
     * Problem: Given an n x n matrix, rotate the matrix by 90 degrees
     * clockwise. The rotation must be done in place.
     *
     * Example: Input: [ [1, 2, 3], [4, 5, 6], [7, 8, 9] ]
     *
     * Output: [ [7, 4, 1], [8, 5, 2], [9, 6, 3] ]
     */
    /**
     * Approach 1: Brute Force (Using Extra Matrix)
     *
     * Idea: - Create a new matrix. - Place matrix[i][j] at position [j][n - 1 -
     * i].
     *
     * Time Complexity: O(n^2) Space Complexity: O(n^2)
     *
     * Stable: Not Applicable
     */
    public static int[][] rotateBrute(int[][] matrix) {
        int n = matrix.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[j][n - 1 - i] = matrix[i][j];
            }
        }

        return result;
    }

    /**
     * Approach 2: Better (Transpose + Reverse Rows)
     *
     * Idea: - Transpose the matrix. - Reverse each row.
     *
     * Time Complexity: O(n^2) Space Complexity: O(1)
     *
     * Stable: Not Applicable
     */
    public static void rotateBetter(int[][] matrix) {
        int n = matrix.length;

        // Transpose
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse each row
        for (int i = 0; i < n; i++) {
            reverseRow(matrix[i]);
        }
    }

    private static void reverseRow(int[] row) {
        int left = 0;
        int right = row.length - 1;

        while (left < right) {
            int temp = row[left];
            row[left] = row[right];
            row[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * Approach 3: Optimal (Layer by Layer Rotation)
     *
     * Idea: - Rotate the matrix layer by layer. - For each layer, swap four
     * elements at a time.
     *
     * Time Complexity: O(n^2) Space Complexity: O(1)
     *
     * Stable: Not Applicable
     */
    public static void rotateOptimal(int[][] matrix) {
        int n = matrix.length;

        for (int layer = 0; layer < n / 2; layer++) {
            int first = layer;
            int last = n - 1 - layer;

            for (int i = first; i < last; i++) {
                int offset = i - first;
                int top = matrix[first][i];

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

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        rotateBetter(matrix);

        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
