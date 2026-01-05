public class SmallestDivisor {

    /**
     * Problem:
     * Find the smallest divisor such that the sum of
     * ceil(arr[i] / divisor) is <= limit.
     */

    /**
     * Helper Method:
     * Computes sum of divisions for a given divisor.
     */
    private static long computeSum(int[] arr, int divisor) {
        long sum = 0;
        for (int num : arr) {
            sum += (num + divisor - 1) / divisor; // ceil division
        }
        return sum;
    }

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Try every divisor from 1 to max element in array
     * - Return first divisor whose sum <= limit
     *
     * Time Complexity: O(maxElement * n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int smallestDivisorBrute(int[] arr, int limit) {
        int max = 0;
        for (int num : arr) {
            max = Math.max(max, num);
        }

        for (int d = 1; d <= max; d++) {
            if (computeSum(arr, d) <= limit) {
                return d;
            }
        }

        return -1;
    }

    /**
     * Approach 2: Binary Search on Answer (Optimal)
     *
     * Idea:
     * - As divisor increases, sum monotonically decreases
     * - Apply binary search on divisor range [1, maxElement]
     *
     * Time Complexity: O(n * log(maxElement))
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int smallestDivisorOptimal(int[] arr, int limit) {
        int low = 1;
        int high = 0;

        for (int num : arr) {
            high = Math.max(high, num);
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (computeSum(arr, mid) <= limit) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 9};
        int limit = 6;

        System.out.println(smallestDivisorBrute(arr, limit));
        System.out.println(smallestDivisorOptimal(arr, limit));
    }
}
