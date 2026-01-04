
public class MinimumDaysToMakeBouquets {

    /**
     * Problem: Find the minimum number of days to make m bouquets using k
     * adjacent bloomed roses.
     */
    /**
     * Helper Method: Checks if it is possible to make m bouquets within given
     * days.
     */
    private static boolean canMake(int[] arr, int m, int k, int days) {
        int bouquets = 0;
        int flowers = 0;

        for (int bloom : arr) {
            if (bloom <= days) {
                flowers++;
            } else {
                bouquets += flowers / k;
                flowers = 0;
            }
        }

        // Handle last segment
        bouquets += flowers / k;

        return bouquets >= m;
    }

    /**
     * Approach 1: Brute Force
     *
     * Idea: - Try every possible day from min bloom to max bloom - Check if m
     * bouquets can be made
     *
     * Time Complexity: O((maxDay - minDay) * n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int minDaysBrute(int[] arr, int m, int k) {
        long required = (long) m * k;
        if (required > arr.length) {
            return -1;
        }

        int minDay = Integer.MAX_VALUE;
        int maxDay = Integer.MIN_VALUE;

        for (int bloom : arr) {
            minDay = Math.min(minDay, bloom);
            maxDay = Math.max(maxDay, bloom);
        }

        for (int day = minDay; day <= maxDay; day++) {
            if (canMake(arr, m, k, day)) {
                return day;
            }
        }

        return -1;
    }

    /**
     * Approach 2: Binary Search on Answer (Optimal)
     *
     * Idea: - Answer lies between min bloom day and max bloom day - If possible
     * on mid day, try smaller days
     *
     * Time Complexity: O(n * log(maxDay)) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int minDaysOptimal(int[] arr, int m, int k) {
        long required = (long) m * k;
        if (required > arr.length) {
            return -1;
        }

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for (int bloom : arr) {
            low = Math.min(low, bloom);
            high = Math.max(high, bloom);
        }

        int ans = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canMake(arr, m, k, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 10, 3, 10, 2};
        int m = 3;
        int k = 1;

        System.out.println(minDaysBrute(arr, m, k));
        System.out.println(minDaysOptimal(arr, m, k));
    }
}
