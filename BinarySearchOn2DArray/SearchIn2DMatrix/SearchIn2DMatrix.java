
public class SearchIn2DMatrix {

    /**
     * Problem: Search for a target in a sorted 2D matrix.
     */
    /**
     * Approach 1: Brute Force
     *
     * Idea: - Traverse each element - Check if any element equals target
     *
     * Time Complexity: O(n * m) Space Complexity: O(1)
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
     * Approach 2: Binary Search (Optimal)
     *
     * Idea: - Treat the 2D matrix as a 1D sorted array - Index mapping: row =
     * mid / m col = mid % m
     *
     * Time Complexity: O(log(n * m)) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static boolean searchOptimal(int[][] mat, int target) {
        int n = mat.length;
        int m = mat[0].length;

        int low = 0;
        int high = n * m - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int row = mid / m;
            int col = mid % m;

            if (mat[row][col] == target) {
                return true;
            } else if (mat[row][col] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        int target = 3;

        System.out.println(searchBrute(mat, target));
        System.out.println(searchOptimal(mat, target));
    }
}
