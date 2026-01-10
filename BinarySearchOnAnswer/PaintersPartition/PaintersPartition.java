public class PaintersPartition {

    /**
     * Problem:
     * Find the minimum time required to paint all boards
     * using K painters, where each painter paints
     * only contiguous boards.
     */

    /**
     * Helper Method:
     * Checks if boards can be painted within maxTime
     * using at most K painters.
     */
    private static boolean canPaint(int[] arr, int K, int maxTime) {
        int painters = 1;
        int time = 0;

        for (int board : arr) {
            if (time + board > maxTime) {
                painters++;
                time = board;
            } else {
                time += board;
            }
        }

        return painters <= K;
    }

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Answer lies between max board length and total sum
     * - Try all possible times and check feasibility
     *
     * Time Complexity: O((sum - max) * n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int paintersPartitionBrute(int[] arr, int K) {
        if (K > arr.length) return -1;

        int max = 0;
        int sum = 0;

        for (int board : arr) {
            max = Math.max(max, board);
            sum += board;
        }

        for (int time = max; time <= sum; time++) {
            if (canPaint(arr, K, time)) {
                return time;
            }
        }

        return -1;
    }

    /**
     * Approach 2: Binary Search on Answer (Optimal)
     *
     * Idea:
     * - Minimum time = max board length
     * - Maximum time = total sum of boards
     * - If feasible, try smaller time
     *
     * Time Complexity: O(n * log(sum))
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int paintersPartitionOptimal(int[] arr, int K) {
        if (K > arr.length) return -1;

        int low = 0;
        int high = 0;

        for (int board : arr) {
            low = Math.max(low, board);
            high += board;
        }

        int ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canPaint(arr, K, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        int K = 2;

        System.out.println(paintersPartitionBrute(arr, K));
        System.out.println(paintersPartitionOptimal(arr, K));
    }
}
