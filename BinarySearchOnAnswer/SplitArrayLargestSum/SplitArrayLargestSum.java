public class SplitArrayLargestSum {

    /**
     * Problem:
     * Split array into K subarrays such that the
     * largest subarray sum is minimized.
     */

    /**
     * Helper Method:
     * Checks if array can be split into at most K subarrays
     * such that no subarray sum exceeds maxSum.
     */
    private static boolean canSplit(int[] arr, int K, int maxSum) {
        int subarrays = 1;
        int currentSum = 0;

        for (int num : arr) {
            if (currentSum + num > maxSum) {
                subarrays++;
                currentSum = num;
            } else {
                currentSum += num;
            }
        }

        return subarrays <= K;
    }

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Answer lies between max element and sum of array
     * - Try all possible values and check feasibility
     *
     * Time Complexity: O((sum - max) * n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int splitArrayBrute(int[] arr, int K) {
        int max = 0;
        int sum = 0;

        for (int num : arr) {
            max = Math.max(max, num);
            sum += num;
        }

        for (int maxSum = max; maxSum <= sum; maxSum++) {
            if (canSplit(arr, K, maxSum)) {
                return maxSum;
            }
        }

        return -1;
    }

    /**
     * Approach 2: Binary Search on Answer (Optimal)
     *
     * Idea:
     * - Minimum possible sum = max element
     * - Maximum possible sum = total sum
     * - If feasible, try smaller maxSum
     *
     * Time Complexity: O(n * log(sum))
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int splitArrayOptimal(int[] arr, int K) {
        int low = 0;
        int high = 0;

        for (int num : arr) {
            low = Math.max(low, num);
            high += num;
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canSplit(arr, K, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] A = {7, 2, 5, 10, 8};
        int K = 2;

        System.out.println(splitArrayBrute(A, K));
        System.out.println(splitArrayOptimal(A, K));
    }
}
