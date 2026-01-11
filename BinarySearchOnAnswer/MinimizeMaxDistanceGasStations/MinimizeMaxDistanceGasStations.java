
public class MinimizeMaxDistanceGasStations {

    /**
     * Problem: Minimize the maximum distance between adjacent gas stations
     * after adding k new stations.
     */
    /**
     * Helper Method: Returns how many gas stations are required to ensure max
     * distance between adjacent stations is <= dist.
     */
    private static int requiredStations(int[] arr, double dist) {
        int count = 0;

        for (int i = 1; i < arr.length; i++) {
            double gap = arr[i] - arr[i - 1];
            count += (int) (gap / dist);
        }

        return count;
    }

    /**
     * Approach 1: Brute Force
     *
     * Idea: - Gradually reduce the distance by small steps - Check if k
     * stations are sufficient
     *
     * Time Complexity: Very high (not practical) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static double minimizeMaxDistanceBrute(int[] arr, int k) {
        double low = 0;
        double high = arr[arr.length - 1] - arr[0];
        double step = 0.0001;

        for (double d = high; d >= low; d -= step) {
            if (requiredStations(arr, d) > k) {
                return d + step;
            }
        }

        return low;
    }

    /**
     * Approach 2: Binary Search on Answer (Optimal)
     *
     * Idea: - Answer lies between 0 and max gap - If required stations <= k,
     * try smaller distance
     *
     * Time Complexity: O(n * log(range / precision)) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static double minimizeMaxDistanceOptimal(int[] arr, int k) {
        double low = 0;
        double high = 0;

        for (int i = 1; i < arr.length; i++) {
            high = Math.max(high, arr[i] - arr[i - 1]);
        }

        double eps = 1e-6;

        while (high - low > eps) {
            double mid = (low + high) / 2;

            if (requiredStations(arr, mid) > k) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return high;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;

        System.out.println(minimizeMaxDistanceOptimal(arr, k));
    }
}
