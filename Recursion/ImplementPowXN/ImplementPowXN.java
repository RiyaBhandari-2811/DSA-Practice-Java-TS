public class ImplementPowXN {

    /**
     * Implement Pow(x, n)
     *
     * Problem:
     * - Given a double x and an integer n, compute x raised to the power n (x^n).
     *
     * Examples:
     * - x = 2, n = 10 → 1024
     * - x = 2, n = -2 → 0.25
     *
     * ------------------------------------------------------------
     * ITERATIVE APPROACH (Binary Exponentiation - Loop)
     *
     * Idea:
     * - Handle negative power by converting:
     *     x^(-n) = 1 / (x^n)
     * - Repeatedly:
     *     - If power is odd, multiply result by current x
     *     - Square x
     *     - Divide power by 2
     * - This reduces the problem size by half each step.
     *
     * Edge Cases:
     * - n == 0 → return 1
     * - n < 0 → convert to reciprocal
     * - n == Integer.MIN_VALUE → use long to avoid overflow
     *
     * Time Complexity: O(log n)
     * - Power is halved at each step.
     *
     * Space Complexity: O(1)
     * - No extra space used.
     */
    public static double myPowIterative(double x, int n) {

        long power = n; // use long to safely handle Integer.MIN_VALUE

        // handle negative power
        if (power < 0) {
            x = 1 / x;
            power = -power;
        }

        double result = 1.0;

        while (power > 0) {

            // if power is odd
            if (power % 2 == 1) {
                result = result * x;
            }

            // square the base
            x = x * x;

            // reduce power
            power = power / 2;
        }

        return result;
    }

    /**
     * ------------------------------------------------------------
     * RECURSIVE APPROACH (Divide and Conquer)
     *
     * Idea:
     * - Handle negative power once at the start.
     * - Recursively divide power by 2.
     * - While returning:
     *     - If n is even → half * half
     *     - If n is odd  → half * half * x
     *
     * Edge Cases:
     * - n == 0 → return 1
     * - n < 0 → return 1 / power(x, -n)
     *
     * Time Complexity: O(log n)
     * - Recursive calls reduce power by half.
     *
     * Space Complexity: O(log n)
     * - Due to recursion call stack.
     */
    public static double myPowRecursive(double x, int n) {
        if (n < 0) {
            return 1.0 / power(x, -(long) n);
        }
        return power(x, n);
    }

    private static double power(double x, long n) {

        // base case
        if (n == 0) {
            return 1.0;
        }

        double half = power(x, n / 2);

        // if n is even
        if (n % 2 == 0) {
            return half * half;
        }

        // if n is odd
        return half * half * x;
    }

    // ------------------------------------------------------------
    // For demonstration / testing
    public static void main(String[] args) {

        double[][] tests = {
            {2, 10},
            {2, -2},
            {5, 0},
            {2, -3}
        };

        String[] names = {
            "case1 x=2, n=10",
            "case2 x=2, n=-2",
            "case3 x=5, n=0",
            "case4 x=2, n=-3"
        };

        for (int i = 0; i < tests.length; i++) {
            double x = tests[i][0];
            int n = (int) tests[i][1];

            System.out.println("Testing " + names[i]);
            System.out.println("Iterative : " + myPowIterative(x, n));
            System.out.println("Recursive : " + myPowRecursive(x, n));
            System.out.println();
        }
    }
}
