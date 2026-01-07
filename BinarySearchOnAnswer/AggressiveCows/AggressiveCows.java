import java.util.Arrays;

public class AggressiveCows {

    /**
     * Problem:
     * Place k cows in stalls such that the minimum distance
     * between any two cows is maximized.
     */

    /**
     * Helper Method:
     * Checks if it is possible to place k cows
     * with at least 'dist' minimum distance.
     */
    private static boolean canPlace(int[] arr, int k, int dist) {
        int cows = 1; // place first cow
        int lastPos = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - lastPos >= dist) {
                cows++;
                lastPos = arr[i];
            }
            if (cows == k) return true;
        }

        return false;
    }

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Sort the stalls
     * - Try all possible distances from 1 to maxDistance
     * - Check feasibility for each distance
     *
     * Time Complexity: O(n * maxDistance)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int aggressiveCowsBrute(int[] arr, int k) {
        Arrays.sort(arr);

        int maxDist = arr[arr.length - 1] - arr[0];

        for (int d = 1; d <= maxDist; d++) {
            if (!canPlace(arr, k, d)) {
                return d - 1;
            }
        }

        return maxDist;
    }

    /**
     * Approach 2: Binary Search on Answer (Optimal)
     *
     * Idea:
     * - Minimum distance = 1
     * - Maximum distance = last stall - first stall
     * - If distance works, try larger
     * - Else try smaller
     *
     * Time Complexity: O(n * log(maxDistance))
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int aggressiveCowsOptimal(int[] arr, int k) {
        Arrays.sort(arr);

        int low = 1;
        int high = arr[arr.length - 1] - arr[0];
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canPlace(arr, k, mid)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 8, 9};
        int k = 3;

        System.out.println(aggressiveCowsBrute(arr, k));
        System.out.println(aggressiveCowsOptimal(arr, k));
    }
}
