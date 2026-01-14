public class SearchRowColSortedMatrix {

    /**
     * Problem:
     * Search for a target in a matrix where each row and
     * each column is sorted.
     */

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Traverse every element
     *
     * Time Complexity: O(n * m)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static boolean searchBrute(int[][] mat, int target) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Approach 2: Staircase Search (Optimal)
     *
     * Idea:
     * - Start from top-right corner
     * - If current > target → move left
     * - If current < target → move down
     *
     * Time Complexity: O(n + m)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static boolean searchOptimal(int[][] mat, int target) {
        int n = mat.length;
        int m = mat[0].length;

        int row = 0;
        int col = m - 1;

        while (row < n && col >= 0) {
            if (mat[row][col] == target) {
                return true;
            } else if (mat[row][col] > target) {
                col--;
            } else {
                row++;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1, 4, 7, 11},
            {2, 5, 8, 12},
            {3, 6, 9, 16},
            {10, 13, 14, 17}
        };

        int target = 5;

        System.out.println(searchBrute(mat, target));
        System.out.println(searchOptimal(mat, target));
    }
}
