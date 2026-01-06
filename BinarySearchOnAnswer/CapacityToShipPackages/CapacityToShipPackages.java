
public class CapacityToShipPackages {

    /**
     * Problem: Find the minimum ship capacity to ship all packages within D
     * days.
     */
    /**
     * Helper Method: Checks if we can ship all packages within D days using
     * given capacity.
     */
    private static boolean canShip(int[] weights, int D, int capacity) {
        int days = 1;
        int load = 0;

        for (int w : weights) {
            if (load + w > capacity) {
                days++;
                load = w;
            } else {
                load += w;
            }
        }

        return days <= D;
    }

    /**
     * Approach 1: Brute Force
     *
     * Idea: - Capacity ranges from max weight to sum of weights - Try every
     * capacity and check feasibility
     *
     * Time Complexity: O((sum - max) * n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int shipWithinDaysBrute(int[] weights, int D) {
        int max = 0;
        int sum = 0;

        for (int w : weights) {
            max = Math.max(max, w);
            sum += w;
        }

        for (int cap = max; cap <= sum; cap++) {
            if (canShip(weights, D, cap)) {
                return cap;
            }
        }

        return -1;
    }

    /**
     * Approach 2: Binary Search on Answer (Optimal)
     *
     * Idea: - Minimum capacity = max weight - Maximum capacity = sum of weights
     * - If a capacity works, try smaller
     *
     * Time Complexity: O(n * log(sum)) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int shipWithinDaysOptimal(int[] weights, int D) {
        int low = 0;
        int high = 0;

        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canShip(weights, D, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int D = 5;

        System.out.println(shipWithinDaysBrute(weights, D));
        System.out.println(shipWithinDaysOptimal(weights, D));
    }
}
