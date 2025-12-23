
import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {

    /**
     * Problem: Print the elements of a matrix in spiral order.
     *
     * Spiral order: left -> right top -> bottom right -> left bottom -> top
     */
    /**
     * Approach: Boundary Traversal (Optimal)
     *
     * Idea: - Maintain four boundaries: top, bottom, left, right - Traverse in
     * spiral while shrinking boundaries
     *
     * Steps: 1. Traverse top row (left -> right) 2. Traverse right column (top
     * -> bottom) 3. Traverse bottom row (right -> left) if top <= bottom
     * 4. Traverse left column (bottom -> top) if left <= right
     *
     * Time Complexity: O(m * n) Space Complexity: O(m * n) (for output list)
     *
     * Stable: Not Applicable
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return result;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right) {

            // top row
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++;

            // right column
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            // bottom row
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--;
            }

            // left column
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        System.out.println(spiralOrder(matrix));
    }
}
