public class PeakElement2D {

    /**
     * Problem:
     * Find any peak element in a 2D matrix.
     * A peak is strictly greater than its four neighbors.
     */

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - For every cell, compare with its 4 neighbors
     * - Return the first valid peak
     *
     * Time Complexity: O(n * m)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int[] findPeakBrute(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                int up = (i > 0) ? mat[i - 1][j] : -1;
                int down = (i < n - 1) ? mat[i + 1][j] : -1;
                int left = (j > 0) ? mat[i][j - 1] : -1;
                int right = (j < m - 1) ? mat[i][j + 1] : -1;

                if (mat[i][j] > up && mat[i][j] > down &&
                    mat[i][j] > left && mat[i][j] > right) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * Approach 2: Binary Search on Columns (Optimal)
     *
     * Idea:
     * - Perform binary search on columns
     * - Pick mid column and find row with maximum value
     * - Compare with left and right neighbors
     *
     * Time Complexity: O(n * log m)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int[] findPeakOptimal(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int low = 0, high = m - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            int maxRow = 0;
            for (int i = 0; i < n; i++) {
                if (mat[i][mid] > mat[maxRow][mid]) {
                    maxRow = i;
                }
            }

            int left = (mid > 0) ? mat[maxRow][mid - 1] : -1;
            int right = (mid < m - 1) ? mat[maxRow][mid + 1] : -1;

            if (mat[maxRow][mid] > left && mat[maxRow][mid] > right) {
                return new int[]{maxRow, mid};
            } else if (left > mat[maxRow][mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[][] mat = {
            {10, 20, 15},
            {21, 30, 14},
            {7, 16, 32}
        };

        int[] res1 = findPeakBrute(mat);
        int[] res2 = findPeakOptimal(mat);

        System.out.println(res1[0] + " " + res1[1]);
        System.out.println(res2[0] + " " + res2[1]);
    }
}
