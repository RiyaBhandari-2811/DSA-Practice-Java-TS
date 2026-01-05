public class KthMissingPositive {

    /**
     * Problem:
     * Find the kth missing positive number from a strictly increasing array.
     */

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Traverse positive numbers starting from 1
     * - Compare with array elements
     * - Decrease k whenever a number is missing
     *
     * Time Complexity: O(n + k)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int findKthMissingBrute(int[] arr, int k) {
        int i = 0;
        int current = 1;

        while (true) {
            if (i < arr.length && arr[i] == current) {
                i++;
            } else {
                k--;
                if (k == 0) return current;
            }
            current++;
        }
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea:
     * - Missing numbers before index i =
     *   arr[i] - (i + 1)
     * - Find the first index where missing >= k
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int findKthMissingOptimal(int[] arr, int k) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int missing = arr[mid] - (mid + 1);

            if (missing < k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low + k;
    }

    public static void main(String[] args) {
        int[] vec = {2, 3, 4, 7, 11};
        int k = 5;

        System.out.println(findKthMissingBrute(vec, k));
        System.out.println(findKthMissingOptimal(vec, k));
    }
}
