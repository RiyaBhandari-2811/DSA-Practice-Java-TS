import java.util.HashSet;
import java.util.Set;

public class FindMissingNumberInArray {

    /**
     * Problem statement:
     * Given an array of length n containing distinct integers in the range [0, n],
     * exactly one number from this range is missing. Return that missing number.
     *
     * Approach 1: Brute force using linear search for each candidate.
     *
     * Idea:
     * - For each candidate value x from 0 to n:
     *   - Scan the entire array and check if x is present.
     *   - The first value that is not found in the array is the missing number.
     * - This approach does not rely on any special property like sorting.
     *
     * Assumptions:
     * - The array contains distinct integers.
     * - All integers are expected to be in the range [0, n] where n = nums.length.
     * - Exactly one number in this range is missing.
     *
     * Edge cases:
     * - nums is null -> IllegalArgumentException.
     * - nums is empty -> IllegalArgumentException (no valid range [0, n] with one missing).
     * - Values outside the range [0, n] or duplicates:
     *   - This violates the problem constraints. The method will either:
     *     - Not find a valid missing number and throw IllegalStateException, or
     *     - Return a value that does not represent the intended missing number.
     *
     * Time Complexity: O(n^2)
     * - For each candidate from 0 to n (O(n)), we potentially scan the full array (O(n)).
     *
     * Space Complexity: O(1)
     * - Uses a constant amount of extra space.
     *
     * Example test cases:
     * - [3, 0, 1]          -> missing 2
     * - [0, 1]             -> missing 2
     * - [9, 6, 4, 2, 3, 5, 7, 0, 1] -> missing 8
     * - [1]                -> missing 0
     * - [0]                -> missing 1
     */
    public static int findMissingBruteLinearSearch(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must be non null and have at least one element");
        }

        int n = nums.length;

        for (int candidate = 0; candidate <= n; candidate++) {
            boolean found = false;

            for (int value : nums) {
                if (value == candidate) {
                    found = true;
                    break;
                }
            }

            if (!found) {
                return candidate;
            }
        }

        throw new IllegalStateException("Input does not conform to problem constraints (no unique missing number in [0, n])");
    }

    /**
     * Approach 2: Hashing using a Set.
     *
     * Idea:
     * - Insert all numbers from the array into a hash set.
     * - Iterate from 0 to n:
     *   - The first number not present in the set is the missing number.
     *
     * Why this is better than the brute force:
     * - Lookup in a hash set is O(1) on average.
     * - Building the set is O(n) and checking all candidates is also O(n).
     * - Overall O(n) instead of O(n^2).
     *
     * Assumptions:
     * - The array contains distinct integers.
     * - All integers are expected to be in the range [0, n].
     * - Exactly one number in this range is missing.
     *
     * Edge cases:
     * - nums is null -> IllegalArgumentException.
     * - nums is empty -> IllegalArgumentException.
     * - Values outside [0, n] or duplicates:
     *   - Violates constraints. Method may return an incorrect value or throw IllegalStateException.
     *
     * Time Complexity: O(n)
     * - Building the set: O(n).
     * - Scanning from 0 to n: O(n).
     *
     * Space Complexity: O(n)
     * - Uses an additional hash set to store up to n elements.
     *
     * Example test cases:
     * - [3, 0, 1]          -> missing 2
     * - [0, 1]             -> missing 2
     * - [9, 6, 4, 2, 3, 5, 7, 0, 1] -> missing 8
     * - [1]                -> missing 0
     * - [0]                -> missing 1
     */
    public static int findMissingUsingHashing(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must be non null and have at least one element");
        }

        int n = nums.length;
        Set<Integer> seen = new HashSet<>();

        for (int value : nums) {
            seen.add(value);
        }

        for (int candidate = 0; candidate <= n; candidate++) {
            if (!seen.contains(candidate)) {
                return candidate;
            }
        }

        throw new IllegalStateException("Input does not conform to problem constraints (no unique missing number in [0, n])");
    }

    /**
     * Approach 3a: Optimal using sum formula.
     *
     * Idea:
     * - For numbers from 0 to n (inclusive), the expected sum is:
     *       expectedSum = n * (n + 1) / 2
     *   where n = nums.length.
     * - Compute the actual sum of all elements in the array.
     * - The missing number is:
     *       missing = expectedSum - actualSum
     *
     * Assumptions:
     * - The array contains distinct integers.
     * - The integers are in the range [0, n].
     * - Exactly one number in this range is missing.
     *
     * Edge cases:
     * - nums is null -> IllegalArgumentException.
     * - nums is empty -> IllegalArgumentException.
     * - Large n may cause integer overflow in n * (n + 1) / 2:
     *   - Here we use long for the intermediate sum to reduce overflow risk.
     * - Values outside [0, n] or duplicates violate the constraints and result in an incorrect answer.
     *
     * Time Complexity: O(n)
     * - One pass to sum the array.
     *
     * Space Complexity: O(1)
     * - Uses a constant amount of extra space.
     *
     * Example test cases:
     * - [3, 0, 1]          -> missing 2
     * - [0, 1]             -> missing 2
     * - [9, 6, 4, 2, 3, 5, 7, 0, 1] -> missing 8
     * - [1]                -> missing 0
     * - [0]                -> missing 1
     */
    public static int findMissingUsingSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must be non null and have at least one element");
        }

        int n = nums.length;
        long expectedSum = (long) n * (n + 1) / 2;

        long actualSum = 0;
        for (int value : nums) {
            actualSum += value;
        }

        long missing = expectedSum - actualSum;
        return (int) missing;
    }

    /**
     * Approach 3b: Optimal using XOR.
     *
     * Idea:
     * - XOR has useful properties:
     *   - a ^ a = 0
     *   - a ^ 0 = a
     *   - XOR is commutative and associative.
     * - XOR all numbers from 0 to n (call this xorRange).
     * - XOR all elements of the array (call this xorArray).
     * - Every number that appears in both will cancel out:
     *       xorRange ^ xorArray = missingNumber
     *
     * Why this is also optimal:
     * - It runs in O(n) time with O(1) space, just like the sum method.
     * - It avoids any risk of overflow because XOR operates on bits, not magnitudes.
     *
     * Assumptions:
     * - The array contains distinct integers.
     * - The integers are in the range [0, n].
     * - Exactly one number in this range is missing.
     *
     * Edge cases:
     * - nums is null -> IllegalArgumentException.
     * - nums is empty -> IllegalArgumentException.
     * - Values outside [0, n] or duplicates:
     *   - Violates constraints. The result will not represent the correct missing number.
     *
     * Time Complexity: O(n)
     * - One pass over the range [0, n] and one pass over the array.
     *
     * Space Complexity: O(1)
     * - Uses a constant number of variables.
     *
     * Example test cases:
     * - [3, 0, 1]          -> missing 2
     * - [0, 1]             -> missing 2
     * - [9, 6, 4, 2, 3, 5, 7, 0, 1] -> missing 8
     * - [1]                -> missing 0
     * - [0]                -> missing 1
     */
    public static int findMissingUsingXor(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array must be non null and have at least one element");
        }

        int n = nums.length;
        int xorRange = 0;
        int xorArray = 0;

        for (int i = 0; i <= n; i++) {
            xorRange ^= i;
        }

        for (int value : nums) {
            xorArray ^= value;
        }

        return xorRange ^ xorArray;
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 0, 1};                               // missing 2
        int[] nums2 = {0, 1};                                  // missing 2
        int[] nums3 = {9, 6, 4, 2, 3, 5, 7, 0, 1};             // missing 8
        int[] nums4 = {1};                                     // missing 0
        int[] nums5 = {0};                                     // missing 1
        int[] nums6 = {4, 2, 1, 0};                            // missing 3

        int[][] tests = {nums1, nums2, nums3, nums4, nums5, nums6};
        String[] names = {"nums1", "nums2", "nums3", "nums4", "nums5", "nums6"};

        for (int i = 0; i < tests.length; i++) {
            int[] arr = tests[i];
            System.out.println("Testing " + names[i] + ":");

            System.out.println("Brute (linear search): " + findMissingBruteLinearSearch(arr));
            System.out.println("Better (hashing):      " + findMissingUsingHashing(arr));
            System.out.println("Optimal (sum):         " + findMissingUsingSum(arr));
            System.out.println("Optimal (XOR):         " + findMissingUsingXor(arr));
            System.out.println();
        }
    }
}
