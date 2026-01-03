public class NthRoot {

    /**
     * Problem:
     * Find the Nth root of M.
     * If the Nth root is not an integer, return -1.
     */

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Try all numbers from 1 to M.
     * - Check if i^N == M.
     *
     * Time Complexity: O(M * N)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int nthRootBrute(int N, int M) {
        for (int i = 1; i <= M; i++) {
            long power = 1;
            for (int j = 1; j <= N; j++) {
                power *= i;
                if (power > M) break;
            }
            if (power == M) return i;
        }
        return -1;
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea:
     * - Search X in range [1, M].
     * - If mid^N == M → answer found.
     * - If mid^N > M → move left.
     * - If mid^N < M → move right.
     *
     * Time Complexity: O(log M * N)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int nthRootOptimal(int N, int M) {
        int low = 1, high = M;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            long power = 1;
            for (int i = 1; i <= N; i++) {
                power *= mid;
                if (power > M) break;
            }

            if (power == M) {
                return mid;
            } else if (power > M) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(nthRootBrute(3, 27));
        System.out.println(nthRootOptimal(3, 27));

        System.out.println(nthRootBrute(2, 10));
        System.out.println(nthRootOptimal(2, 10));
    }
}
