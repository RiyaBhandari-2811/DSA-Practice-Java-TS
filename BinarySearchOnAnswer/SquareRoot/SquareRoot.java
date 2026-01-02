public class SquareRoot {

    /**
     * Problem:
     * Find the square root of a number.
     * Return only the floor value.
     */

    /**
     * Approach 1: Brute Force
     *
     * Idea:
     * - Try all numbers starting from 1.
     * - Stop when square exceeds n.
     *
     * Time Complexity: O(√n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int sqrtBrute(int n) {
        if (n == 0 || n == 1) return n;

        int i = 1;
        while (i * i <= n) {
            i++;
        }

        return i - 1;
    }

    /**
     * Approach 2: Binary Search (Optimal)
     *
     * Idea:
     * - Square root lies between 1 and n.
     * - Use binary search to find largest value whose square ≤ n.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int sqrtOptimal(int n) {
        if (n == 0 || n == 1) return n;

        int low = 1, high = n;
        int ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if ((long) mid * mid <= n) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans; // or high 
    }

    public static void main(String[] args) {
        System.out.println(sqrtBrute(16));
        System.out.println(sqrtOptimal(16));

        System.out.println(sqrtBrute(10));
        System.out.println(sqrtOptimal(10));
    }
}
