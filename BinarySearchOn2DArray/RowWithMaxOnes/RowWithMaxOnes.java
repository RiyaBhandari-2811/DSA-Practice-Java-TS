
public class RowWithMaxOnes {

    /**
     * Problem: Find the index of the row with maximum number of 1s in a binary
     * matrix with sorted rows.
     */
    /**
     * Approach 1: Brute Force
     *
     * Idea: - Count number of 1s in each row - Track the row with maximum count
     *
     * Time Complexity: O(n * m) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int rowWithMaxOnesBrute(int[][] mat) {
        int maxOnes = 0;
        int rowIndex = -1;

        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    count++;
                }
            }

            if (count > maxOnes) {
                maxOnes = count;
                rowIndex = i;
            }
        }

        return rowIndex;
    }

    /**
     * Helper Method: Finds first index of 1 using binary search
     */
    private static int firstOneIndex(int[] row) {
        int low = 0, high = row.length - 1;
        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (row[mid] == 1) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea: - For each row, find first occurrence of 1 - Number of 1s = m -
     * firstIndex
     *
     * Time Complexity: O(n * log m) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int rowWithMaxOnesOptimal(int[][] mat) {
        int maxOnes = 0;
        int rowIndex = -1;
        int m = mat[0].length;

        for (int i = 0; i < mat.length; i++) {
            int firstOne = firstOneIndex(mat[i]);
            if (firstOne != -1) {
                int count = m - firstOne;
                if (count > maxOnes) {
                    maxOnes = count;
                    rowIndex = i;
                }
            }
        }

        return rowIndex;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {0, 0, 1, 1},
            {0, 1, 1, 1},
            {0, 0, 0, 1}
        };

        System.out.println(rowWithMaxOnesBrute(mat));
        System.out.println(rowWithMaxOnesOptimal(mat));
    }
}
