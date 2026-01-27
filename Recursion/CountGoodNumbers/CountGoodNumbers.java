
public class CountGoodNumbers {

    /**
     * Problem: Count total number of good digit strings of length n.
     *
     * A digit string is good if: - Digits at even indices are even (0, 2, 4, 6,
     * 8) - Digits at odd indices are prime (2, 3, 5, 7)
     *
     * Leading zeros are allowed. Return answer modulo (10^9 + 7).
     */
    private static final long MOD = 1_000_000_007;

    /**
     * ------------------------------------------------------------ Approach 1:
     * Brute Force (Conceptual Only)
     *
     * Idea: - Generate all digit strings of length n - Validate each index
     * based on rules
     *
     * Why not implemented: - Total strings = 10^n - n can be as large as 10^15
     *
     * Time Complexity: O(10^n * n) Space Complexity: O(n)
     *
     * Stable: Yes
     */
    // ❌ Not implemented due to constraints
    /**
     * ------------------------------------------------------------ Approach 2:
     * Mathematical Counting (Linear Multiplication)
     *
     * Idea: - Count even and odd indices - Multiply 5 for even index, 4 for odd
     * index - Take modulo at each step
     *
     * Drawback: - Loop runs n times → too slow for large n
     *
     * Time Complexity: O(n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int countGoodNumbersBetter(long n) {
        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;

        long result = 1;

        for (long i = 0; i < evenCount; i++) {
            result = (result * 5) % MOD;
        }

        for (long i = 0; i < oddCount; i++) {
            result = (result * 4) % MOD;
        }

        return (int) result;
    }

    /**
     * ------------------------------------------------------------ Approach 3:
     * Optimal (Iterative Binary Exponentiation)
     *
     * Idea: - Use formula: 5^(evenCount) * 4^(oddCount) % MOD - Compute powers
     * in O(log n)
     *
     * Time Complexity: O(log n) Space Complexity: O(1)
     *
     * Stable: Yes
     */
    public static int countGoodNumbersOptimal(long n) {
        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;

        long evenWays = modPowIterative(5, evenCount);
        long oddWays = modPowIterative(4, oddCount);

        return (int) ((evenWays * oddWays) % MOD);
    }

    /**
     * Iterative Binary Exponentiation
     */
    private static long modPowIterative(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }

    /**
     * ------------------------------------------------------------ Approach 4:
     * Optimal (Recursive Binary Exponentiation)
     *
     * Idea: - Same math as iterative solution - Use divide and conquer
     * recursion
     *
     * Time Complexity: O(log n) Space Complexity: O(log n) // recursion stack
     *
     * Stable: Yes
     */
    public static int countGoodNumbersRecursive(long n) {
        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;

        long evenWays = modPowRecursive(5, evenCount);
        long oddWays = modPowRecursive(4, oddCount);

        return (int) ((evenWays * oddWays) % MOD);
    }

    /**
     * Recursive Binary Exponentiation
     */
    private static long modPowRecursive(long base, long exp) {
        if (exp == 0) {
            return 1;
        }

        long half = modPowRecursive(base, exp / 2);
        long result = (half * half) % MOD;

        if ((exp & 1) == 1) {
            result = (result * base) % MOD;
        }

        return result;
    }

    /**
     * ------------------------------------------------------------ Main Method
     * (Sanity Tests)
     */
    public static void main(String[] args) {
        System.out.println(countGoodNumbersOptimal(1));    // 5
        System.out.println(countGoodNumbersOptimal(4));    // 400
        System.out.println(countGoodNumbersOptimal(50));   // 564908303

        System.out.println(countGoodNumbersRecursive(50)); // 564908303
    }
}
